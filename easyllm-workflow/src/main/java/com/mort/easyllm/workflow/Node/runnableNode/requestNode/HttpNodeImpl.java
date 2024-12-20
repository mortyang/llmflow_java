package com.mort.easyllm.workflow.Node.runnableNode.requestNode;

import com.mort.easyllm.workflow.Node.chainNode.InfoNode;
import com.mort.easyllm.workflow.annotation.node.Node;
import com.mort.easyllm.workflow.annotation.node.PropertiesInject;
import com.mort.easyllm.workflow.Node.runnableNode.requestNode.utils.HttpUtil;
import com.mort.easyllm.workflow.Node.runnableNode.NormalRunnableNode;
import com.mort.easyllm.workflow.Node.runnableNode.requestNode.properties.HttpNodeProperties;
import io.reactivex.functions.Consumer;
import lombok.Getter;
import okhttp3.Response;


@Node(nodeType = "HttpNode")
public class HttpNodeImpl implements NormalRunnableNode {

    @PropertiesInject
    private HttpNodeProperties properties;


    @Getter
    private enum HTTP_METHOD_ENUMS {

        /**
         * @Author Mort
         * @Date 2024-07-22
         */
        GET("get"),
        /**
         * @Author Mort
         * @Date 2024-07-22
         */
        POST("post");

        private final String method;

        HTTP_METHOD_ENUMS(String method) {
            this.method = method;
        }

    }


    @Override
    public String run(InfoNode infoNode, Consumer<String> callback) {
        if (HTTP_METHOD_ENUMS.GET.getMethod().equals(properties.getMethod())) {
            try (Response response = HttpUtil.HTTP_CLIENT.newCall(
                            HttpUtil.generateGetRequest(properties.getUrl(), properties.getHeaders()))
                    .execute()) {
                if (response.body() != null) {
                    return response.body().string();
                }
                return "没有获取到任何信息";
            } catch (Exception e) {
                return "Http请求错误";
            }
        } else if (HTTP_METHOD_ENUMS.POST.getMethod().equals(properties.getMethod())) {
            try (Response response = HttpUtil.HTTP_CLIENT.newCall(
                    HttpUtil.generatePostRequest(
                            properties.getUrl(),
                            properties.getHeaders(),
                            HttpUtil.gennerateRequestBody(properties.getBody(), properties.getHeaders() == null ? null : properties.getHeaders().get("Content-Type")))
            ).execute()) {
                if (response.body() != null) {
                    return response.body().string();
                }
                return "没有获取到任何信息";
            } catch (Exception e) {
                return "Http请求错误";
            }
        }
        return "不存在的请求方法";
    }

}
