package com.mort.easyllm.Node.branchNode;

import com.alibaba.fastjson2.JSONObject;
import com.mort.easyllm.annotation.node.Node;
import com.mort.easyllm.Node.branchNode.properties.NormalJudgeNodeProperties;
import com.mort.easyllm.Node.InfoNode.InfoNode;

import java.util.Map;
import java.util.Objects;

/**
 * @author Mort
 */
@Node(nodeType = "NormalJudgeNode")
public class NormalJudgeNodeImpl implements BranchNode {

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
