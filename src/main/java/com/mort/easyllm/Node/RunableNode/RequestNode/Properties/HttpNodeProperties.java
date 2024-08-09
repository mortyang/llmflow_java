package com.mort.easyllm.Node.RunableNode.RequestNode.Properties;


import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import com.mort.easyllm.Annotation.Node.NodePropertiesField;
import com.mort.easyllm.Utils.ValidateUtil;
import lombok.*;


import javax.validation.constraints.NotBlank;
import java.util.Map;

@Builder
@Getter
@NodePropertiesField(nodeType = "HttpNode")
public class HttpNodeProperties {

//    @NotBlank(message = "Http的url不能为空")
    private String url;

//    @NotBlank(message = "Http的method不能为空")
    private String method;

    private Map<String, String> headers;

    private String body;


    public static HttpNodeProperties jsonObjectConvert(JSONObject properties) {
        HttpNodeProperties temp = HttpNodeProperties.builder()
                .url(properties.getString("url"))
                .body(properties.getString("body"))
                .method(properties.getString("method"))
                .headers(properties.getObject("headers", new TypeReference<>() {
                }))
                .build();
        ValidateUtil.validateAndThrow(temp);
        return temp;
    }

}
