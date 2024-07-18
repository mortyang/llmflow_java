package com.mort.easyllm.Node.BranchNode.Properties;

import com.mort.easyllm.Node.InfoNode.InfoNode;
import lombok.Builder;

import java.util.Map;

@Builder
public class NormalJudgeNodeProperties {

    private Map<String,String> conditionToNodeMap;

    private InfoNode defaultNode;

}
