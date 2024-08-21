package com.mort.easyllm.workflow.Node.runnableNode.branchNode;

import com.mort.easyllm.common.annotation.node.Node;
import com.mort.easyllm.common.annotation.node.PropertiesInject;
import com.mort.easyllm.workflow.Node.runnableNode.branchNode.properties.NormalJudgeNodeProperties;
import com.mort.easyllm.workflow.Node.chainNode.InfoNode;

import java.util.Map;
import java.util.Objects;


/**
 * @author Mort
 */
@Node(nodeType = "NormalJudgeNode")
public class NormalJudgeNodeImpl implements BranchRunnableNode {

    @PropertiesInject
    private NormalJudgeNodeProperties properties;


    @Override
    public InfoNode run(String input, Map<String, InfoNode> nextNodeMap) {
        for (Map.Entry<String, String> conditionEntry : properties.getNodeNameToConditionMap().entrySet()) {
            if (Objects.equals(input, conditionEntry.getValue())) {
                return nextNodeMap.get(conditionEntry.getKey());
            }
        }
        return null;
    }


}
