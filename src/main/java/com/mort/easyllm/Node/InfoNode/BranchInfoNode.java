package com.mort.easyllm.Node.InfoNode;

import com.alibaba.fastjson2.JSONObject;
import com.mort.easyllm.Utils.NodeFactory;
import com.mort.easyllm.Node.BranchNode.BranchNode;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


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

    //Node不允许被回收，无需清理
    private final ConcurrentHashMap<Thread, InfoNode> nextNodes = new ConcurrentHashMap<>();


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

    @Override
    public InfoNode getNextNode() {
        return this.nextNodes.get(Thread.currentThread());
    }

    public void setNextNode(InfoNode infoNode) {
        this.nextNodes.put(Thread.currentThread(), infoNode);
    }


    @Override
    public String runNode(String input) {
        InfoNode nextNode = this.branchNode.run(input, nextNodeMap);
        if (nextNode == null) {
            nextNode = this.nextNodeMap.get(this.defaultNodeName);
        }
        this.setNextNode(nextNode);
        log.info("判断节点：{}，下一节点：{}", this.getNodeName(), this.getNextNode().getNodeName());
        return input;
    }


}
