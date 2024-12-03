import {useVueFlow} from '@vue-flow/core'
import {ref, watch} from 'vue'
import {getPropertiesTemplate} from "../utils/NodeProperties.js";
import useWorkFlowStore from "../../../store/WorkFlow.js";

/**
 * @returns {string} - A unique counter.
 */


const state = {
    draggedType: ref(null),
    isDragOver: ref(false),
    isDragging: ref(false),
}

let counter = 0
function getId(nodeType) {
    return `${nodeType}_${counter++}`
}


export default function useDragAndDrop() {
    const {draggedType, isDragOver, isDragging} = state

    const {addNodes, screenToFlowCoordinate, onNodesInitialized, updateNode,getNodes} = useVueFlow()

    const workFlowStore = useWorkFlowStore()

    watch(isDragging, (dragging) => {
        document.body.style.userSelect = dragging ? 'none' : ''
    })


    function setNodeCounter (newID){
        counter = newID
    }

    function getNodeCounter (){
        console.log(counter)
        return counter
    }

    function onDragStart(event, type) {
        if (event.dataTransfer) {
            event.dataTransfer.setData('application/vueflow', type)
            event.dataTransfer.effectAllowed = 'move'
        }

        draggedType.value = type
        isDragging.value = true

        document.addEventListener('drop', onDragEnd)
    }

    /**
     * Handles the drag over event.
     *
     * @param {DragEvent} event
     */
    function onDragOver(event) {
        event.preventDefault()

        if (draggedType.value) {
            isDragOver.value = true

            if (event.dataTransfer) {
                event.dataTransfer.dropEffect = 'move'
            }
        }
    }

    function onDragLeave() {
        isDragOver.value = false
    }

    function onDragEnd() {
        isDragging.value = false
        isDragOver.value = false
        draggedType.value = null
        document.removeEventListener('drop', onDragEnd)
    }

    function onDrop(event) {
        const position = screenToFlowCoordinate({
            x: event.clientX,
            y: event.clientY,
        })

        const nodeId = getId(draggedType.value)

        const newNode = {
            id: nodeId,
            type: draggedType.value,
            position,
            data: getPropertiesTemplate(draggedType.value),
        }

        /**
         * Align node position after drop, so it's centered to the mouse
         * We can hook into events even in a callback, and we can remove the event listener after it's been called.
         */
        const {off} =  onNodesInitialized(() => {
            updateNode(nodeId, (node) => ({
                position: {
                    x: node.position.x - node.dimensions.width / 2,
                    y: node.position.y - node.dimensions.height / 2
                },
            }))
            off()
        })
        addNodes(newNode)
        workFlowStore.nodes.push(newNode)

        console.log("当前全部节点",workFlowStore.nodes)
    }

    return {
        draggedType,
        isDragOver,
        isDragging,
        onDragStart,
        onDragLeave,
        onDragOver,
        onDrop,
        setNodeCounter,
        getNodeCounter
    }
}
