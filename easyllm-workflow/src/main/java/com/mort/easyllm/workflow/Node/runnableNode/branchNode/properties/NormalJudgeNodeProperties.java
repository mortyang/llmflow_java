package com.mort.easyllm.workflow.Node.runnableNode.branchNode.properties;

import com.mort.easyllm.workflow.annotation.node.NodeProperties;
import com.mort.easyllm.workflow.parameter.ConcatenatingString;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Map;


/**
 * @author Mort
 */

@Data
@NodeProperties(nodeType = "NormalJudgeNode")
public class NormalJudgeNodeProperties {

    @NotNull(message = "输入不允许为空")
    private ConcatenatingString input;

    @NotNull(message = "节点表不允许为空")
    private Map<String, String> nodeNameToConditionMap;

    @NotBlank(message = "分支节点必须有默认节点")
    private String defaultNodeName;

}
