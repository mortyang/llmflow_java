package com.mort.easyllm.Node.BranchNode.Properties;

import com.mort.easyllm.Node.InfoNode.InfoNode;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
public class NormalJudgeNodeProperties {

    private Map<String,String> conditionToNodeMap;

    private InfoNode defaultNode;

}
