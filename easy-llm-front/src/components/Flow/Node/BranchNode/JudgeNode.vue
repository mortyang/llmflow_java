<script setup>
import {defineProps} from "vue";
import {Handle, Position} from '@vue-flow/core'
import {Plus} from "@element-plus/icons-vue";
import {newNodePropertiesGetter} from "../../utils/NodeProperties.js";
import useWorkFlowStore from "../../../../store/WorkFlow.js";

const props = defineProps({
  id: {
    type: String,
    required: true,
  },
  data: {
    type: Object,
    required: true,
  },
})
const emits = defineEmits(['updateNodeInternals'])
const workFlowStore = useWorkFlowStore()
const nodeData = workFlowStore.nodes.find(item => item.id === props.id).data


function handleHeaderAdd() {
  const condition = newNodePropertiesGetter.judge.conditionsGetter()
  condition.handle = `source_${nodeData.conditions.length}`
  nodeData.conditions.push(condition)
  emits('updateNodeInternals')
}

function isValidConnection(connection) {
  let con1 = connection.source !== connection.target
  let con2 = connection.target !== "endNode"
  // if(result){
  //   let data = getNode.data.conditions
  // }
  return con1 && con2
}

</script>


<script>
export default {
  inheritAttrs: false,
}
</script>

<template>
  <Handle id="target-left" type="target" :position="Position.Left"/>
  <div>
    <div>{{ data.label == null ? props.id : data.label }}</div>
    <el-divider style="margin: 10px auto;"/>
    <div style="margin-bottom: 5px" v-for='(single,index) in props.data.conditions'>
      <span>{{ index + 1 }}</span>:
      <span>{{ single.label }}</span>
      <Handle :style="{top: String(index * 26+60)+'px'}"
              :id="`source_${index}`"
              type="source"
              :position="Position.Right"
              :is-valid-connection="isValidConnection"
      />
    </div>
    <div style="margin-bottom: 10px">
      <span>默认节点</span>
      <Handle style="bottom: 40px;top:auto" id="source_default" type="source"
              :position="Position.Right"/>
    </div>
    <el-button @click.stop="handleHeaderAdd()">
      <el-icon>
        <Plus/>
      </el-icon>
    </el-button>
  </div>
</template>
