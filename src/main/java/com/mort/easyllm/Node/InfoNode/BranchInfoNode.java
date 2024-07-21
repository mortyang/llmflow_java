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
@Getter
@Slf4j
public class BranchInfoNode extends InfoNode {

    private final Map<String, InfoNode> nextNodeMap = new HashMap<>();

    private final String defaultNodeName;

    //执行单元
    private final BranchNode branchNode;

    @Builder(builderMethodName = "branchInfoNodeBuilder")
    public BranchInfoNode(@NonNull String nodeName,
                          @NonNull String nodeType,
                          @NonNull String defaultNodeName,
                          JSONObject properties,
                          Boolean isBranchNode) {
        super(nodeName, nodeType, isBranchNode, properties);
        this.branchNode = NodeFactory.getNodeByName(nodeType, properties, BranchNode.class);
        this.defaultNodeName = defaultNodeName;
    }


    public RunNodeReturn runNode(String input) {
        RunNodeReturn runNodeReturn = new RunNodeReturn();
        runNodeReturn.setDeliverMessage(input);

        InfoNode nextNode = this.branchNode.run(input, nextNodeMap);
        if (nextNode == null) {
            nextNode = this.nextNodeMap.get(this.defaultNodeName);
        }
        runNodeReturn.setNextNode(nextNode);
        log.info("判断节点：{}，下一节点：{}", this.getNodeName(), runNodeReturn.getNextNode().getNodeName());
        return runNodeReturn;
    }


}
