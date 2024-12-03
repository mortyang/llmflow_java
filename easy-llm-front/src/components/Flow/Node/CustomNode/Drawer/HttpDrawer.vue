<template>
  <el-divider/>
  <el-form
      label-width="auto"
      :model="nodeData"
      style="max-width: 600px"
  >
    <el-form-item label="输入：">
      <el-input type="textarea" v-model="nodeData.input.text"></el-input>
    </el-form-item>
    <el-form-item label="Method" label-position="right">
      <el-radio-group
          v-model="nodeData.method"
      >
        <el-radio-button value="get">Get</el-radio-button>
        <el-radio-button value="post">Post</el-radio-button>
      </el-radio-group>
    </el-form-item>
    <el-form-item label="URL:">
      <el-input clearable v-model="nodeData.url"/>
    </el-form-item>
    <el-form-item label="Headers">
      <div style="display: flex;flex-direction: column">
        <el-button style="width: 50px" @click="handleHeaderAdd">
          <el-icon>
            <Plus/>
          </el-icon>
        </el-button>
        <div style="margin-top: 10px" v-for="header in nodeData.headers">
          <el-input
              v-model="header.key"
              style="width: 130px"
              placeholder="Please input"
              clearable
          />
          :
          <el-input
              v-model="header.value"
              style="width: 130px"
              placeholder="Please input"
              clearable
          />
        </div>
      </div>
    </el-form-item>
    <el-form-item label="Body:">
      <el-input
          clearable
          v-model="nodeData.body"
          style="width: 240px"
          :autosize="{ minRows: 2 }"
          type="textarea"
          placeholder="Please input"
          size="large"
      />
    </el-form-item>
  </el-form>
</template>

<script setup>
import {Plus} from "@element-plus/icons-vue"
import useWorkFlowStore from "../../../../../store/WorkFlow.js";
import {} from "vue";

const props = defineProps({
  id: {
    type: String,
    required: true,
  }
})
const workFlowStore = useWorkFlowStore()
const nodeData = workFlowStore.nodes.find(item => item.id === props.id).data

const headers = nodeData.headers


let id = 1
function handleHeaderAdd() {
  headers.push({
    id: id++,
    key: "",
    value: ""
  })
}
</script>

<style scoped>

</style>