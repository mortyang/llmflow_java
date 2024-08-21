package com.mort.easyllm.workflow.Node.runnableNode.llmNode.properties;

import com.alibaba.fastjson2.annotation.JSONField;
import com.mort.easyllm.common.annotation.node.NodeProperties;
import com.mort.easyllm.llm.tongyi.TongyiProperties;
import com.mort.easyllm.workflow.parameter.ConcatenatingString;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Mort
 */
@Data
@NodeProperties(nodeType = "TongyiNode")
public class TongyiNodeProperties {

    @JSONField(name = "sysMsg")
    private String sysMsg;

    @JSONField(name = "properties")
    private TongyiProperties properties;

    @JSONField(name ="needSessionContext")
    private Integer needSessionContext;

    @JSONField(name = "tongyiProperties")
    private TongyiProperties tongyiProperties;

    @JSONField(name = "input")
    private ConcatenatingString input;

}
