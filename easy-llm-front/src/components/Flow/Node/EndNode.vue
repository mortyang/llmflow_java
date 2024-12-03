<script setup>
import {Handle, Position} from '@vue-flow/core'

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

function isValidConnection(connection) {
  return connection.source !== "startNode"
}


</script>

<script>
export default {
  inheritAttrs: false,
}
</script>

<template>
  <Handle type="target" :position="Position.Left" :is-valid-connection="isValidConnection"/>
  <div>{{ props.data.label == null ? props.id : props.data.label }}</div>
</template>
