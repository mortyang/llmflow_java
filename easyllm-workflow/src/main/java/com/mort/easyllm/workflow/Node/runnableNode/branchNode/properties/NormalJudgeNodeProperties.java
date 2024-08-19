package com.mort.easyllm.workflow.Node.runnableNode.branchNode.properties;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.TypeReference;
import com.mort.easyllm.common.annotation.node.NodePropertiesField;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.util.Map;

/**
 * @author Mort
 */
@Builder
@Getter
@NodePropertiesField(nodeType = "NormalJudgeNode")
public class NormalJudgeNodeProperties {

    /**
     *
     * @Author Mort
     * @Date 2024-08-08
     */
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
