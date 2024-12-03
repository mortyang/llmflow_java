import {defineStore} from "pinia";
import {ref} from 'vue';

const useWorkFlowStore = defineStore('vue-flow-pinia', () => {
    let nodes = ref([
        {
            id: 'startNode',
            type: "start",
            position: {x: 50, y: 50},
            data: {label: '开始节点', nextNodeName: ""},
        },
        {
            id: 'endNode',
            type: "end",
            position: {x: 500, y: 50},
            data: {label: '结束节点', fatherNodeNameList: []},
        }
    ])

    const edges = ref([])

    const reset = () => {
        edges.value = [];
        nodes.value = [];
    };

    const log = () => {
        console.log('nodes', nodes.value, 'edges', edges.value);
    };

    const addNode = (node) => {
        nodes.value.push(node.value)
    }

    const replaceNodes = (newNodes) => {
        nodes.value = newNodes
    }


    return {
        nodes,
        edges,
        reset,
        log,
        replaceNodes,
        addNode
    };
});

export default useWorkFlowStore;
