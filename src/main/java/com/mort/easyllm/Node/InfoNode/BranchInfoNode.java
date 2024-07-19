package com.mort.easyllm.Node.InfoNode;

import com.alibaba.fastjson2.JSONObject;
import com.mort.easyllm.Utils.NodeFactory;
import com.mort.easyllm.Node.BranchNode.BranchNode;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;


/**
 * @author Mort
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Slf4j
public class BranchInfoNode extends InfoNode {

    private Map<String, InfoNode> nextNodeMap = new HashMap<>();
    private String defaultNodeName;

    //执行单元
    private BranchNode branchNode;

    @Builder(builderMethodName = "branchInfoNodeBuilder")
    public BranchInfoNode(@NonNull String nodeName,
                          @NonNull String nodeType,
                          @NonNull String defaultNodeName,
                          JSONObject properties,
                          Boolean isBranchNode) {
        super(nodeName, nodeType, isBranchNode, properties, null);
        this.branchNode = (BranchNode) NodeFactory.getRunableNodeByName(nodeType, properties);
        this.defaultNodeName = defaultNodeName;
    }

    public void runNode() {
        clear();
        this.setNextNode(this.branchNode.run(this.getInput(), nextNodeMap));
        if (this.getNextNode() == null) {
            this.setNextNode(this.nextNodeMap.get(this.defaultNodeName));
        }
        log.info("判断节点：{}，下一节点：{}",this.getNodeName(),this.getNextNode().getNodeName());
        this.getNextNode().setInput(this.getInput());
    }

    @Override
    public void clear() {
        super.clear();
        this.setNextNode(null);
    }

}
