<script setup>
import {defineProps} from 'vue'
import useWorkFlowStore from "../../../../../store/WorkFlow.js";

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
  <el-input
      v-model="nodeData.input.text"
      style="width: 30%"
      type="textarea"
      placeholder="输入/input"
  />
  <div style="margin-top: 20px" v-for="condition in nodeData.conditions">
    <el-input
        v-model="condition.label"
        style="width: 30%"
        placeholder="标题"
    />
    <el-input
        v-model="condition.condition"
        style="width: 30%"
        placeholder="匹配正则"
    />
  </div>
</template>

<style scoped>

</style>