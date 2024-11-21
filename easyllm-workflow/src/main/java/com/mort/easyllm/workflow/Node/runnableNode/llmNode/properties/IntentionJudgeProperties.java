package com.mort.easyllm.workflow.Node.runnableNode.llmNode.properties;

import com.mort.easyllm.workflow.annotation.node.NodeProperties;
import com.mort.easyllm.llm.supplier.tongyi.TongyiProperties;
import com.mort.easyllm.workflow.parameter.ConcatenatingString;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Mort
 */
@Data
@NodeProperties(nodeType = "IntentionJudgeNode")
public class IntentionJudgeProperties {

    @NotNull(message = "输入不为空")
    private ConcatenatingString input;

    @NotEmpty(message = "意图不允许为空")
    private List<String> intentions;

    private TongyiProperties llmProperties;


}
