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

const handleConnectable = (node,connectedEdges) => {
  return connectedEdges.length < 1;
};

function isValidConnection(connection) {
  let con1 = connection.source !== connection.target
  let con2 = connection.target !== "endNode"
  return con1 && con2
}

</script>

<script>
export default {
  inheritAttrs: false,
}
</script>

<template>
  <Handle  type="source" :position="Position.Right" :is-valid-connection="isValidConnection" :connectable="handleConnectable"/>
  <div>{{ props.data.label==null ? props.id : props.data.label }}</div>
</template>
