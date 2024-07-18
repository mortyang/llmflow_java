package com.mort.easyllm.Node.RunableNode.RequestNode.Properties;


import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import lombok.*;

import java.util.Map;

@Builder
@Getter
public class HttpNodeProperties {

    @NonNull
    private String url;

    private Map<String, String> headers;

    private String body;


    public static HttpNodeProperties jsonObjectConvert(JSONObject properties) {
        return HttpNodeProperties.builder()
                .url(properties.getString("url"))
                .body(properties.getString("body"))
                .headers(properties.getObject("headers", new TypeReference<>() {
                }))
                .build();
    }

}
