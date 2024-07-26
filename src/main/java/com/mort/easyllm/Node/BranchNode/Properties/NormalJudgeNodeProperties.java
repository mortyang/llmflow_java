package com.mort.easyllm.Node.BranchNode.Properties;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import com.mort.easyllm.Annotation.Node.NodePropertiesField;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.util.Map;

/**
 * @author Mort
 */
@Builder
@Getter
@NodePropertiesField(nodeName = "NormalJudgeNode")
public class NormalJudgeNodeProperties {

    @NonNull
    private Map<String,String> conditionToNodeMap;

    public static NormalJudgeNodeProperties jsonObjectConvert(JSONObject properties) {
        return NormalJudgeNodeProperties.builder()
                .conditionToNodeMap(properties.getObject("conditionToNodeMap", new TypeReference<>() {
                }))
                .build();
    }

}
