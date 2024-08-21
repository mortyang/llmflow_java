package com.mort.easyllm.workflow.Node.runnableNode.branchNode.properties;

import com.alibaba.fastjson2.annotation.JSONField;
import com.mort.easyllm.common.annotation.node.NodeProperties;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.Map;


/**
 * @author Mort
 */

@Data
@NodeProperties(nodeType = "NormalJudgeNode")
public class NormalJudgeNodeProperties {

    /**
     * @Author Mort
     * @Date 2024-08-08
     */
    @JSONField(name = "nodeNameToConditionMap")
    private Map<String,String> nodeNameToConditionMap;

    @JSONField(name = "defaultNodeName")
    private String defaultNodeName;

}
