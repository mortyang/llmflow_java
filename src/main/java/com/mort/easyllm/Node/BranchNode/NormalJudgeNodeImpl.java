package com.mort.easyllm.Node.BranchNode;

import com.mort.easyllm.Node.BranchNode.Properties.NormalJudgeNodeProperties;
import com.mort.easyllm.Node.InfoNode.InfoNode;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class NormalJudgeNodeImpl implements BranchNode {

    private final NormalJudgeNodeProperties properties;

    public NormalJudgeNodeImpl(Object properties) {
        this.properties = new NormalJudgeNodeProperties();
        this.properties.setConditionToNodeMap(new HashMap<>());
    }

    @Override
    public InfoNode run(String input, Map<String, InfoNode> nextNodeMap) {
        for (Map.Entry<String, String> conditionEntry : properties.getConditionToNodeMap().entrySet()) {
            if (Objects.equals(input, conditionEntry.getKey())) {
                return nextNodeMap.get(conditionEntry.getValue());
            }
        }
        return null;
    }


}
