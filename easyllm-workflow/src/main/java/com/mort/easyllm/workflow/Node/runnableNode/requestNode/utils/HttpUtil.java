package com.mort.easyllm.workflow.Node.runnableNode.requestNode.utils;

import okhttp3.*;

import java.util.Map;
import java.util.Objects;

public class HttpUtil {


    public static final OkHttpClient HTTP_CLIENT = new OkHttpClient();

    public static Request generatePostRequest(String url, Map<String, String> headersMap, RequestBody requestBody) {
        Headers.Builder headers = new Headers.Builder();
        if (headersMap != null) {
            for (Map.Entry<String, String> entry : headersMap.entrySet()) {
                headers.add(entry.getKey(), entry.getValue());
            }
        }
        return new Request.Builder()
                .url(url)
                .headers(headers.build())
                .post(requestBody)
                .build();
    }

    public static Request generateGetRequest(String url, Map<String, String> headersMap) {
        Headers.Builder headers = new Headers.Builder();
        if (headersMap != null) {
            for (Map.Entry<String, String> entry : headersMap.entrySet()) {
                headers.add(entry.getKey(), entry.getValue());
            }
        }
        return new Request.Builder()
                .url(url)
                .headers(headers.build())
                .get()
                .build();
    }

    public static RequestBody gennerateRequestBody(String bodyText, String type) {
        MediaType mediaType;
        // TODO 异常处理
        mediaType = MediaType.Companion.parse(Objects.requireNonNullElse(type, "application/json;charset=utf-8"));
        return RequestBody.Companion.create(bodyText, mediaType);
    }


}
