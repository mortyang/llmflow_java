<script setup>
import {ref, onMounted} from "vue"
import workflowApi from "../api/workFlowApi.js";
import useGotWorkFlowsStore from "../store/gotWorkFlows.js";
import {useRoute, useRouter} from "vue-router";

const useGotWorkFlows = useGotWorkFlowsStore()
const router = useRouter()

onMounted(() => {
  workflowApi.getWorkflowList().then(res => {
    useGotWorkFlows.setWorkFlowList(res.data)
  })
})

function toWorkFlowEditPage(data) {
  router.push({
    path: "/WorkFlowBuilder",
    query: {
      id: data.id,
      workflowName: data.workFlowName
    }
  })
}


</script>

<template>
  <div class="mainBox">
    <div style="height: 5vh"></div>
    <div style="display: flex; flex-wrap: wrap;justify-content: flex-start">
      <el-card
          @click="toWorkFlowEditPage(data)"
          v-for="data in useGotWorkFlows.workFlowList"
          style="width: 20vw;margin: 3vh 3vw">
        <template #header>{{ data.id }}</template>
        {{ data.workFlowName }}
      </el-card>
    </div>
  </div>
</template>

<style scoped>
.mainBox {
  width: 80vw;
  height: 80vh;
  margin: auto;
  top: 50px;
  position: relative;
  background-color: #d88b3b;
  overflow: auto;
  border-radius: 30px;
}
</style>