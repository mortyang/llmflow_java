import axios from "axios";
import {ElMessage} from "element-plus";


const _axios = axios.create({
    baseURL: "http://localhost:8080",
    headers: {
          // 特定请求的 Content-Type
    }
});

//配置请求的参数


_axios.interceptors.request.use(
    (config) => {
        //配置token
        if (localStorage.getItem("token")) {
            config.headers["Authorization"] = localStorage.getItem("token");
        }
        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
);
//配置响应的参

_axios.interceptors.response.use(
    (response) => {
        if (response.data.status === 401) {
            localStorage.removeItem("token");
            ElMessage({
                message: "验证失败：" + response.data.message,
                showClose: true,
                type: "error",
                duration: 2500,
            });
        }
        return response.data;
    },

    (error) => {
        ElMessage({
            message: "网络异常" + error.message,
            type: "error",
            duration: 2500,
            showClose: true,
        });
        console.log(error);
        return Promise.reject(error);
    }
);

export default _axios;
