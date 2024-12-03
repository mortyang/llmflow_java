import { createApp } from 'vue'
import App from "./App.vue"
import { createPinia } from 'pinia'
import router from './router/index.js'
import ElementPlus from 'element-plus'


const pinia = createPinia()
const app = createApp(App)


app.use(router)
app.use(pinia)
app.use(ElementPlus, { size: 'small', zIndex: 3000 })


app.mount('#app')