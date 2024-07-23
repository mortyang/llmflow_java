package com.mort.easyllm.Node.RunableNode.LLMNode.Properties;

import com.alibaba.fastjson2.JSONObject;
import com.mort.easyllm.Annotation.NodeProperties;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.List;

/**
 * @author Mort
 */
@Data
@Builder
@NodeProperties(nodeName = "IntentionJudgeNode")
public class IntentionJudgeProperties {

    @NonNull
    private List<String> intentions;

    private String modelName;

    private String modelProviderName;


    public static IntentionJudgeProperties jsonObjectConvert(JSONObject properties) {
        return IntentionJudgeProperties.builder()
                .intentions(properties.getList("intentions",String.class))
                .modelName(properties.getString("modelName"))
                .build();
    }


}
