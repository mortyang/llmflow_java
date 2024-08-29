package com.mort.easyllm.workflow.Node.runnableNode.llmNode.properties;

import com.alibaba.fastjson2.annotation.JSONField;
import com.mort.easyllm.workflow.annotation.node.NodeProperties;
import com.mort.easyllm.llm.supplier.tongyi.TongyiProperties;
import com.mort.easyllm.workflow.parameter.ConcatenatingString;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author Mort
 */
@Data
@NodeProperties(nodeType = "TongyiNode")
public class TongyiNodeProperties {

    @NotNull(message = "输入不允许为空")
    private ConcatenatingString input;

    private ConcatenatingString sysMsg;



    @Valid
    @NotNull(message = "大模型配置不为空")
    @JSONField(name = "llmProperties")
    private TongyiProperties llmProperties;


}
