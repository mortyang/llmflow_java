package com.mort.easyllm.workFlow.Node.runnableNode.branchNode;

import com.alibaba.fastjson2.JSONObject;
import com.mort.easyllm.annotation.node.Node;
import com.mort.easyllm.workFlow.Node.runnableNode.branchNode.properties.NormalJudgeNodeProperties;
import com.mort.easyllm.workFlow.Node.chainNode.InfoNode;

import java.util.Map;
import java.util.Objects;

/**
 * @author Mort
 */
@Node(nodeType = "NormalJudgeNode")
public class NormalJudgeNodeImpl implements BranchRunnableNode {

    private final NormalJudgeNodeProperties properties;

    public NormalJudgeNodeImpl(JSONObject properties) {
        this.properties = NormalJudgeNodeProperties.jsonObjectConvert(properties);
    }

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
