<script setup>
import useDragAndDrop from './useDnD.js'
import {useVueFlow} from "@vue-flow/core";

const {onDragStart} = useDragAndDrop()
const {toObject} = useVueFlow()

function click() {
  console.log(toObject())
}

</script>

<template>
  <aside>
    <div @click="click" class="description">拖拽到画布</div>
    <div class="nodes">
      <div class="vue-flow__node-default"
           :draggable="true"
           @dragstart="onDragStart($event, 'judge')">
        JudgeNode
      </div>
      <div class="vue-flow__node-default"
           :draggable="true"
           @dragstart="onDragStart($event, 'llm')">
        LLMNode
      </div>
      <div class="vue-flow__node-default"
           :draggable="true"
           @dragstart="onDragStart($event, 'llmJudge')">
        LLMJudgeNode
      </div>
      <div class="vue-flow__node-default"
           :draggable="true"
           @dragstart="onDragStart($event, 'http')">
        httpNode
      </div>
      <div class="vue-flow__node-default"
           :draggable="true"
           @dragstart="onDragStart($event, 'parameterExtraction')">
        parameterExtraction
      </div>
    </div>
  </aside>
</template>

<style>

</style>
