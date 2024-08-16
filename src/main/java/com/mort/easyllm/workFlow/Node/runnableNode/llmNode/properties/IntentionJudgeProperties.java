package com.mort.easyllm.workFlow.Node.runnableNode.llmNode.properties;

import com.alibaba.fastjson2.JSONObject;
import com.mort.easyllm.annotation.node.NodePropertiesField;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author Mort
 */
@Data
@Builder
@NodePropertiesField(nodeType = "IntentionJudgeNode")
public class IntentionJudgeProperties {

    @NotEmpty(message = "意图不允许为空")
    private List<String> intentions;

    @NotEmpty(message = "请指定是否需要记忆")
    private Boolean hasMemory;

    private String modelName;

    private String modelProviderName;

    private String needSessionContext;


    public static IntentionJudgeProperties jsonObjectConvert(JSONObject properties) {
        return IntentionJudgeProperties.builder()
                .intentions(properties.getList("intentions",String.class))
                .modelName(properties.getString("modelName"))
                .hasMemory(properties.getBoolean("hasMemory"))
                .build();
    }


}
