package com.mort.easyllm.Node.RunableNode.LLMNode.Properties;

import com.alibaba.fastjson2.JSONObject;
import com.mort.easyllm.Annotation.Node.NodePropertiesField;
import lombok.Builder;
import lombok.Getter;

/**
 * @author Mort
 */
@Getter
@Builder
@NodePropertiesField(nodeName = "TongyiNode")
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
