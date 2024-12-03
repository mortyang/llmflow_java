<script setup>
import {defineProps} from "vue";
import useWorkFlowStore from "../../../../../store/WorkFlow.js";
import TongyiConfiger from "../../../component/ModelConfiger/TongyiConfiger.vue";

const props = defineProps({
  id: {
    type: String,
    required: true,
  }
})
const workFlowStore = useWorkFlowStore()
const nodeData = workFlowStore.nodes.find(item => item.id === props.id).data

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
    <el-form-item label="sysMsg:">
      <el-input clearable v-model="nodeData.sysMsg.text"/>
    </el-form-item>
  </el-form>
  <el-divider/>
  <tongyi-configer :id="props.id"></tongyi-configer>
</template>

<style scoped>

</style>