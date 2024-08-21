package com.mort.easyllm.workflow.Node.runnableNode.llmNode.properties;

import com.alibaba.fastjson2.annotation.JSONField;
import com.mort.easyllm.common.annotation.node.NodeProperties;
import com.mort.easyllm.llm.tongyi.TongyiProperties;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author Mort
 */
@Data
@NodeProperties(nodeType = "IntentionJudgeNode")
public class IntentionJudgeProperties {

    @NotEmpty(message = "意图不允许为空")
    @JSONField(name = "intentions")
    private List<String> intentions;

    @JSONField(name = "modelName")
    private String modelName;

    @JSONField(name = "modelProviderName")
    private String modelProviderName;

    @JSONField(name = "needSessionContext")
    private String needSessionContext;

    @JSONField(name = "tongyiProperties")
    private TongyiProperties tongyiProperties;


}
