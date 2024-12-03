import axios from "./axios.js";
import qs from 'qs'

const workflowApi = {
    addWorkflow(workFlowName) {
        return axios({
            url: "/workFlow/addWorkflow",
            method: "POST",
            data: qs.stringify({
                workFlowName: workFlowName
            }),
        });
    },

    getWorkflowList() {
        return axios({
            url: "/workFlow/getWorkflowList",
            method: "POST",
        });
    },

    getWorkflowListByPermissionGroup(PermissionGroup) {
        return axios({
            url: "/workFlow/getWorkflowListByPermissionGroup",
            method: "POST",
            data: qs.stringify({
                permissionGroup: PermissionGroup
            }),
        });
    },

    getWorkflowJson(id) {
        return axios({
            url: "/workFlow/getWorkflowJson",
            method: "POST",
            data:  qs.stringify({
                id: id
            }),
        });
    },

    uploadWorkFlow(id,frontJson,workFlowList) {
        console.log(JSON.stringify({
            id:id,
            workFlowList:workFlowList,
        }))
        return axios({
            url: "/workFlow/uploadWorkFlow",
            method: "POST",
            headers: {"Content-Type": "application/json"},
            data: {
                id:id,
                workFlowList:workFlowList,
                frontJson:frontJson,
            }
        });
    }
};

export default workflowApi;