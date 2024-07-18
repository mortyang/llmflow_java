package com.mort.easyllm.Node.BranchNode;

import com.mort.easyllm.Node.InfoNode.InfoNode;

import java.util.Map;

public class NormalJudgeNodeImpl implements BranchNode {

    private Map<String, InfoNode> branchNodeList;

    public NormalJudgeNodeImpl(Object properties) {
        System.out.println("NormalJudgeNodeImpl properties:" + properties.toString());
    }

    @Override
    public InfoNode run(String input, Map<String, InfoNode> infoNode) {
        return null;
    }

}
