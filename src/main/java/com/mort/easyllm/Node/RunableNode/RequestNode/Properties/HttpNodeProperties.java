package com.mort.easyllm.Node.RunableNode.RequestNode.Properties;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.mort.easyllm.Utils.JsonUtil;
import lombok.*;

import java.util.Map;

@Builder
@Getter
public class HttpNodeProperties {

    @NonNull
    private String url;

    private Map<String, String> headers;

    private String body;


    public static HttpNodeProperties jsonObjectConvert(JsonNode properties){
        return HttpNodeProperties.builder()
                .url(properties.path("url").asText())
                .body(properties.path("body").asText())
                .headers(JsonUtil.objectMapper.convertValue(properties.path("headers"), new TypeReference<>() {
                }))
                .build();
    }

}
