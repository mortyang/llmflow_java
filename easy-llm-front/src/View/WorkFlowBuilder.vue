<script setup>
import '@vue-flow/core/dist/style.css';
import '@vue-flow/core/dist/theme-default.css';
import '../components/Flow/assets/flow.css';

import {ref, onMounted} from 'vue'
import {VueFlow, useVueFlow, ConnectionMode} from "@vue-flow/core"
import useDragAndDrop from "../components/Flow/siderBar/useDnD.js"
import JudgeDrawer from "../components/Flow/Node/BranchNode/Drawer/JudgeDrawer.vue";
import JudgeNode from "../components/Flow/Node/BranchNode/JudgeNode.vue";
import EdgeWithButton from "../components/Flow/edges/EdgeWithButton.vue";
//component
import DropzoneBackground from "../components/Flow/assets/DropzoneBackground.vue"
import Sidebar from "../components/Flow/siderBar/Sidebar.vue"
import HttpDrawer from "../components/Flow/Node/CustomNode/Drawer/HttpDrawer.vue";
import llmJudgeDrawer from "../components/Flow/Node/CustomNode/Drawer/llmJudgeDrawer.vue";
import llmDrawer from "../components/Flow/Node/CustomNode/Drawer/llmDrawer.vue"
//Node
import startNode from "../components/Flow/Node/StartNode.vue";
import EndNode from "../components/Flow/Node/EndNode.vue";
import LlmNode from "../components/Flow/Node/CustomNode/llmNode.vue";
import HttpNode from "../components/Flow/Node/CustomNode/HttpNode.vue";
import LlmJudgeNode from "../components/Flow/Node/CustomNode/llmJudgeNode.vue";
import useWorkFlowStore from "../store/WorkFlow.js";
import {Back, Select} from "@element-plus/icons-vue";
import {useRoute, useRouter} from "vue-router";
import workflowApi from "../api/workFlowApi.js";
import converter from "../components/Flow/utils/serverSideObjectConverter.js";
import ParameterExtractionNode from "../components/Flow/Node/CustomNode/ParameterExtractionNode.vue";
import ParameterExtractionDrawer from "../components/Flow/Node/CustomNode/Drawer/parameterExtractionDrawer.vue";

const {onDragOver, onDrop, onDragLeave, isDragOver, setID} = useDragAndDrop()
const useDnd = useDragAndDrop();
const workFlowStore = useWorkFlowStore()
const useFlow = useVueFlow()
const route = useRoute()
const router = useRouter()

let loading = ref(false)


onMounted(() => {
  if (!route.query.id || !route.query.workflowName) {
    router.push("/")
  }
})

function toIndex() {
  router.push("/")
}


useFlow.onConnect((connection) => {
  useFlow.addEdges(connection)
})


useFlow.onPaneReady(() => {
  loading.value = true
  workflowApi.getWorkflowJson(route.query.id).then((res) => {
    const save = JSON.parse(res.data)
    console.log(save)
    if (save) {
      workFlowStore.replaceNodes(save.workFlow.nodes)
      useFlow.fromObject(save.workFlow)
      useDnd.setNodeCounter(save.count)
    }
    loading.value = false
  })
})


useFlow.onNodesChange((change) => {
  change.forEach((item) => {
    if (item.type === "remove") {
      const index = workFlowStore.nodes.findIndex(element => element.id === item.id);
      if (index !== -1) {
        workFlowStore.nodes.splice(index, 1);
      }
    }
  })
})


useFlow.onEdgesChange((changes) => {
  console.log(changes)
  for (const change of changes) {
    if (change.type === "add") {
      handleEdgeAdd(change)
    } else if (change.type === "remove") {
      handleEdgeRemove(change)
    }
  }
})


function handleEdgeAdd(change) {
  const sourceNode = change.item.sourceNode
  const targetNode = change.item.targetNode
  if (sourceNode.data.isBranchNode) {
    sourceNode.data.conditions.forEach((condition) => {
      if (change.item.sourceHandle === "source_default") {
        sourceNode.data.defaultNodeName = targetNode.id
      } else if (condition.handle === change.item.sourceHandle) {
        condition.nextNode = targetNode.id
      }
    })
    sourceNode.data.nextNodeNameList.push(targetNode.id)
  } else {
    sourceNode.data.nextNodeName = targetNode.id
  }
  console.log(targetNode.data)
  targetNode.data.fatherNodeNameList.push(sourceNode.id)
}

function handleEdgeRemove(change) {
  const sourceNode = useFlow.findNode(change.source)
  const targetNode = useFlow.findNode(change.target)
  if (sourceNode.data.isBranchNode) {
    sourceNode.data.conditions.forEach((condition) => {
      if (change.sourceHandle === "source_default") {
        sourceNode.data.defaultNodeName = ""
      } else if (condition.handle === change.sourceHandle) {
        condition.nextNode = ""
      }
    })
    for (let i in sourceNode.data.nextNodeNameList) {
      if (sourceNode.data.nextNodeNameList[i] === targetNode.id) {
        sourceNode.data.nextNodeNameList.splice(i, 1);
      }
    }
  } else {
    sourceNode.data.nextNodeName = ""
  }
  for (let i in targetNode.data.fatherNodeNameList) {
    if (targetNode.data.fatherNodeNameList[i] === sourceNode.id) {
      targetNode.data.fatherNodeNameList.splice(i, 1);
    }
  }
}


//////////////////保存
let isValidWorkFlow = true;

function handleSave() {
  let temp = convertToServer()
  if(!isValidWorkFlow) {
    ElMessage.error("无效的工作流，请检查是否所有路径最终流向endNode")
    return
  }
  workflowApi.uploadWorkFlow(route.query.id,
      JSON.stringify({
        workFlow: useFlow.toObject(),
        count: useDnd.getNodeCounter()
      }), temp)
      .then(res => {
        console.log(res)
        if (res.code === "SUCCESS") {
          ElMessage.success(res.message)
        }
      }).catch(err => {
        console.log(err)
        ElMessage.error(err.message)
  })
}

let nodeConvertList = []

function convertToServer() {
  nodeConvertList = []
  recursion(useFlow.findNode(useFlow.findNode("startNode").data.nextNodeName))
  return nodeConvertList
}

function recursion(obj) {
  //TODO bug
  if (!obj) {
    isValidWorkFlow = false;
    return;
  }

  if (obj.id === "endNode") return
  nodeConvertList.push(converter.convertServerNode(obj))
  if (obj.data.isBranchNode) {
    obj.data.conditions.forEach((condition) => {
      recursion(useFlow.findNode(condition.nextNode))
    })
    recursion(useFlow.findNode(obj.data.defaultNodeName))
    return;
  }
  recursion(useFlow.findNode(obj.data.nextNodeName))
}


//////////////////点击

let drawer = ref(false)
let drawerClass = ref("")
let drawerId = ref("")


function handleNodeClick(params) {
  if (!clickTwice()) {
    return
  }
  if (params.node.type !== "start" && params.node.type !== "end") {
    drawerId.value = params.node.id
    drawer.value = true
    drawerClass.value = params.node.type
  }
}

let count = 0
function clickTwice() {
  if (count < 1) {
    count++
    setTimeout(() => {
      count = 0
    }, 500)
    return false
  }
  count = 0
  return true
}


</script>

<template>
  <div v-loading="loading" style="width: 100vw;height: 100vh">
    <div class="bar">
      <el-button @click="toIndex" size="large" circle>
        <el-icon>
          <Back/>
        </el-icon>
      </el-button>
      <el-button @click="handleSave" size="large" style="margin-left: 30px" circle>
        <el-icon>
          <Select/>
        </el-icon>
      </el-button>
    </div>
    <div class="dnd-flow" @drop="onDrop">
      <VueFlow
          :edges="workFlowStore.edges"
          :nodes="workFlowStore.nodes"
          :connection-mode="ConnectionMode.Strict"
          @dragover="onDragOver"
          @dragleave="onDragLeave"
          @NodeClick="handleNodeClick"
      >
        <DropzoneBackground
            :style="{backgroundColor: isDragOver ? '#e7f3ff' : 'transparent',transition: 'background-color 0.2s ease',}">
          <p v-if="isDragOver" style="z-index: 99">Drop here</p>
        </DropzoneBackground>
        <template #edge-button="buttonEdgeProps">
          <EdgeWithButton
              :id="buttonEdgeProps.id"
              :source-x="buttonEdgeProps.sourceX"
              :source-y="buttonEdgeProps.sourceY"
              :target-x="buttonEdgeProps.targetX"
              :target-y="buttonEdgeProps.targetY"
              :source-position="buttonEdgeProps.sourcePosition"
              :target-position="buttonEdgeProps.targetPosition"
              :marker-end="buttonEdgeProps.markerEnd"
              :style="buttonEdgeProps.style"
          />
        </template>
        <template #node-start="props">
          <start-node :id="props.id" :data="props.data"/>
        </template>
        <template #node-end="props">
          <end-node :id="props.id" :data="props.data"/>
        </template>
        <template #node-llm="props">
          <llm-node :id="props.id" :data="props.data"/>
        </template>
        <template #node-http="props">
          <http-node :id="props.id" :data="props.data"/>
        </template>
        <template #node-llmJudge="props">
          <llm-judge-node :id="props.id" :data="props.data"/>
        </template>
        <template #node-judge="props">
          <judge-node :id="props.id" :data="props.data"/>
        </template>
        <template #node-parameterExtraction="props">
          <parameter-extraction-node :id="props.id" :data="props.data"/>
        </template>
      </VueFlow>
      <Sidebar/>

      <el-drawer
          @closed="drawerClass = ''"
          size="50%"
          v-model="drawer"
          title="I am the title"
          :with-header="false">
        <div v-if="drawerClass==='judge'">
          <JudgeDrawer :id="drawerId"/>
        </div>
        <div v-else-if="drawerClass==='llm'">
          <llm-drawer :id="drawerId"></llm-drawer>
        </div>
        <div v-else-if="drawerClass==='llmJudge'">
          <llm-judge-drawer :id="drawerId"></llm-judge-drawer>
        </div>
        <div v-else-if="drawerClass==='http'">
          <http-drawer :id="drawerId"/>
        </div>
        <div v-else-if="drawerClass==='parameterExtraction'">
          <parameter-extraction-drawer :id="drawerId"/>
        </div>
        <div v-else>错误</div>
      </el-drawer>

    </div>
  </div>
</template>

<style>
.bar {
  position: absolute;
  z-index: 9999999;
  margin: 50px 100px;
  background: rgba(255, 255, 255, 0.09);
  padding: 10px 10px;
  backdrop-filter: blur(10px);
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  text-align: center;
}

</style>

