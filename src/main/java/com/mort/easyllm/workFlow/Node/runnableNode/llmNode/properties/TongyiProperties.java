package com.mort.easyllm.workFlow.Node.runnableNode.llmNode.properties;

import com.alibaba.fastjson2.JSONObject;
import com.mort.easyllm.annotation.node.NodePropertiesField;
import lombok.Builder;
import lombok.Getter;

/**
 * @author Mort
 */
@Getter
@Builder
@NodePropertiesField(nodeType = "TongyiNode")
public class TongyiProperties {

    private String sysMsg;

    private String modelName;

    public static TongyiProperties jsonObjectConvert(JSONObject properties) {
        return TongyiProperties.builder()
                .sysMsg(properties.getString("sysMsg"))
                .modelName(properties.getString("modelName"))
                .build();
    }
}
