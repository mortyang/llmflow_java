<script setup>
import {defineProps} from "vue";
import useWorkFlowStore from "../../../../../store/WorkFlow.js";
import {Plus} from "@element-plus/icons-vue";
import TongyiConfiger from "../../../component/ModelConfiger/TongyiConfiger.vue";

const props = defineProps({
  id: {
    type: String,
    required: true,
  }
})
const workFlowStore = useWorkFlowStore()
const nodeData = workFlowStore.nodes.find(item => item.id === props.id).data

//需要id
function handleAdd() {
  nodeData.intentions.push({intention: ""})
}

</script>

<template>
  <el-form
      label-width="auto"
      :model="nodeData"
      style="max-width: 600px"
  >
    <el-form-item label="输入:">
      <el-input clearable v-model="nodeData.input.text"/>
    </el-form-item>
    <el-form-item label="Conditions">
      <el-button @click="handleAdd">
        <el-icon>
          <Plus/>
        </el-icon>
      </el-button>
      <div
          style="width: 100%;display: flex;flex-direction: column;align-content: flex-start;margin-top:10px;flex-wrap: wrap">
        <div v-for="intention in nodeData.intentions">
          <el-input
              v-model="intention.intention"
              style="width: 130px"
              placeholder="Please input"
              clearable
          />
        </div>
      </div>
    </el-form-item>
  </el-form>
  <el-divider/>
  <tongyi-configer :id="props.id"></tongyi-configer>
</template>

<style scoped>

</style>