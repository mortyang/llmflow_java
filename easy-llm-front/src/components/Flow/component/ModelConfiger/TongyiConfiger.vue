<script setup>
import {defineProps} from "vue";
import useWorkFlowStore from "../../../../store/WorkFlow.js";

const props = defineProps({
  id: {
    type: String,
    required: true,
  }
})

const workFlowStore = useWorkFlowStore()
const llmProperties = workFlowStore.nodes.find(item => item.id === props.id).data.llmProperties

</script>

<template>
  <el-form :model="llmProperties">
    <el-form-item label="modelName:">
      <el-input v-model="llmProperties.modelName"/>
    </el-form-item>
    <el-form-item label="apiKey:">
      <el-input v-model="llmProperties.apiKey"/>
    </el-form-item>
    <el-form-item label="temperature:">
      <el-input-number :precision="2" :step="0.1" v-model="llmProperties.temperature"/>
    </el-form-item>
    <el-form-item label="maxTokens:">
      <el-input-number :precision="0" :step="1" v-model="llmProperties.maxTokens"/>
    </el-form-item>
    <el-form-item label="topP:">
      <el-input-number :precision="2" :step="0.1" v-model="llmProperties.topP"/>
    </el-form-item>
    <el-form-item label="enableSearch:">
      <el-radio-group v-model="llmProperties.enableSearch">
        <el-radio-button :value="true">是</el-radio-button>
        <el-radio-button :value="false">否</el-radio-button>
      </el-radio-group>
    </el-form-item>
    <el-form-item label="useContext:">
      <el-radio-group v-model="llmProperties.useContext">
        <el-radio-button :value="true">是</el-radio-button>
        <el-radio-button :value="false">否</el-radio-button>
      </el-radio-group>
    </el-form-item>
    <el-form-item label="contextLength:">
      <el-input-number :precision="0" :step="1" v-model="llmProperties.contextLength"/>
    </el-form-item>
  </el-form>
</template>

<style scoped>

</style>