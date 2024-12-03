<script setup>
import { Handle, Position,useHandle } from '@vue-flow/core'
import {onMounted} from "vue";


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

</script>

<script>
export default {
  inheritAttrs: false,
}
</script>

<template>
  <Handle id="target-left" type="target" :position="Position.Left"/>
  <Handle id="target-right" type="source" :position="Position.Right"/>
  <div>{{ props.data.label==null ? props.id : props.data.label }}</div>
</template>
