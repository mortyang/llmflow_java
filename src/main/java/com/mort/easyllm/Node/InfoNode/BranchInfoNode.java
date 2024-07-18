package com.mort.easyllm.Node.InfoNode;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONPObject;
import com.mort.easyllm.Config.NodeFactory;
import com.mort.easyllm.Node.BranchNode.BranchNode;
import lombok.*;

import java.util.HashMap;
import java.util.Map;


/**
 * @author Mort
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BranchInfoNode extends InfoNode {

    private Map<String, InfoNode> nextNodeMap = new HashMap<>();

    private BranchNode branchNode;

    @Builder(builderMethodName = "branchInfoNodeBuilder")
    public BranchInfoNode(@NonNull String nodeName,
                          @NonNull String nodeType,
                          JSONObject properties,
                          Boolean isBranchNode) {
        super(nodeName, nodeType, isBranchNode, properties, null);
        this.branchNode = (BranchNode) NodeFactory.getRunableNodeByName(nodeType, properties);
    }


    public void runNode() {
        this.setOutput(this.getInput());
        this.setNextNode(this.branchNode.run(this.getInput(), nextNodeMap));
        this.getNextNode().setInput(this.getOutput());
    }

}