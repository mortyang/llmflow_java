package com.mort.easyllm.workflow.Node.runnableNode.requestNode.properties;

import com.mort.easyllm.workflow.annotation.node.NodeProperties;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.Map;

@Data
@NodeProperties(nodeType = "HttpNode")
public class HttpNodeProperties {

    @NotBlank(message = "Http的url不能为空")
    private String url;

    @NotBlank(message = "Http的method不能为空")
    private String method;

    private Map<String, String> headers;

    private String body;

}
