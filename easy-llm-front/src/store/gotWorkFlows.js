import {defineStore} from "pinia";
import {ref} from 'vue';

const useGotWorkFlowsStore = defineStore('GotWorkFlows-pinia', () => {
    let workFlowList = ref([])

    const setWorkFlowList = (workFlows) => {
        workFlowList.value = workFlows
    }

    return {
        workFlowList,
        setWorkFlowList,
    }

})

export default useGotWorkFlowsStore;
