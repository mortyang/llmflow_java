package com.mort.easyllm.workflow.Node.runnableNode.requestNode.properties;


import com.alibaba.fastjson2.annotation.JSONField;
import com.mort.easyllm.common.annotation.node.NodeProperties;
import lombok.*;


import javax.validation.constraints.NotBlank;
import java.util.Map;

@Data
@NodeProperties(nodeType = "HttpNode")
public class HttpNodeProperties {

    @NotBlank(message = "Http的url不能为空")
    @JSONField(name = "url")
    private String url;

    @NotBlank(message = "Http的method不能为空")
    @JSONField(name = "method")
    private String method;

    @JSONField(name = "headers")
    private Map<String, String> headers;

    @JSONField(name = "body")
    private String body;

}
