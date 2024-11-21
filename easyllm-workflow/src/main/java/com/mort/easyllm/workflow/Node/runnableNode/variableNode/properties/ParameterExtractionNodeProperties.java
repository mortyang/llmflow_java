package com.mort.easyllm.workflow.Node.runnableNode.variableNode.properties;

import com.alibaba.fastjson2.annotation.JSONField;
import com.mort.easyllm.llm.supplier.tongyi.TongyiProperties;
import com.mort.easyllm.workflow.annotation.node.NodeProperties;
import com.mort.easyllm.workflow.parameter.ConcatenatingString;
import com.mort.easyllm.workflow.parameter.ExtractionParameter;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NodeProperties(nodeType = "ParameterExtractionNode")
public class ParameterExtractionNodeProperties {

    @NotNull(message = "输入不允许为空")
    private ConcatenatingString input;

    @NotNull(message = "")
    @JSONField(name = "useSequentialCheck", defaultValue = "true")
    private Boolean useSequentialCheck;

    @Valid
    @NotNull(message = "参数表不允许为空")
    private List<ExtractionParameter> extractionParameterList;

    @Valid
    @NotNull(message = "大模型配置不允许为空")
    @JSONField(name = "llmProperties")
    private TongyiProperties llmProperties;

}
