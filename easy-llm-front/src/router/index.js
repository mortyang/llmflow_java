import {createWebHistory, createRouter} from 'vue-router'

import WorkFlowBuilder from "../View/WorkFlowBuilder.vue";
import index from "../View/index.vue";


const router = createRouter({
    history: createWebHistory(),
    routes: [
        {path: "/", component: index},
        {path: "/WorkFlowBuilder", component: WorkFlowBuilder},
    ],
})


export default router