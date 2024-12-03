<script setup>
import {defineProps} from "vue";
import useWorkFlowStore from "../../../../../store/WorkFlow.js";
import TongyiConfiger from "../../../component/ModelConfiger/TongyiConfiger.vue";
import {CirclePlus} from "@element-plus/icons-vue";

const props = defineProps({
  id: {
    type: String,
    required: true,
  }
})
const workFlowStore = useWorkFlowStore()
const nodeData = workFlowStore.nodes.find(item => item.id === props.id).data

const parameters = nodeData.extractionParameterList

function handleParameterAdd() {
  parameters.push({
    parameterName:"demo",
    description:"",
    parameterType:"",
    required:false,
  })
}

</script>

<template>
  <el-row>
    <el-form>
      <el-form-item label="输入：">
        <el-input v-model="nodeData.input.text"></el-input>
      </el-form-item>
      <el-form-item label="使用连续解析：">
        <el-switch v-model="nodeData.useSequentialCheck"/>
      </el-form-item>
      <el-form-item label="使用连续解析：">
        <el-button @click="handleParameterAdd">
          <el-icon><CirclePlus/></el-icon>
        </el-button>
      </el-form-item>
      <el-col style="border-style:solid;border-color:#98ddff; padding: 10px;border-radius: 10px" v-for="parameter in parameters">
        <el-form-item label="变量名">
          <el-input v-model="parameter.parameterName"></el-input>
        </el-form-item>
        <el-form-item label="描述(prompt)：">
          <el-input type="textarea" v-model="parameter.description"></el-input>
        </el-form-item>
        <el-form-item label="类型">
          <el-select v-model="parameter.parameterType" placeholder="请选择类型">
            <el-option label="String" value="String"/>
            <el-option label="Integer" value="Integer"/>
          </el-select>
        </el-form-item>
        <el-form-item label="必选：">
          <el-switch v-model="parameter.required"/>
        </el-form-item>
      </el-col>

    </el-form>

    <el-divider/>
    <tongyi-configer :id="props.id"></tongyi-configer>
  </el-row>
</template>

<style scoped>

</style>