package com.mort.easyllm.Node.branchNode.properties;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import com.mort.easyllm.annotation.node.NodePropertiesField;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.util.Map;

@Builder
@Getter
@NodePropertiesField(nodeType = "llmBranchJudgeNode")
public class LLMBranchJudgeNodeProperties {

    @NonNull
    private Map<String,String> nodeNameToConditionMap;

    @NonNull
    private String defaultNodeName;


    public static NormalJudgeNodeProperties jsonObjectConvert(JSONObject properties) {
        return NormalJudgeNodeProperties.builder()
                .defaultNodeName(properties.getString("defaultNodeName"))
                .nodeNameToConditionMap(properties.getObject("nodeNameToConditionMap", new TypeReference<>() {
                }))
                .build();
    }

    
}
