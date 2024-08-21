package com.mort.easyllm.workflow.Node.chainNode;

import com.alibaba.fastjson2.JSONObject;
import com.mort.easyllm.workflow.Node.runnableNode.NodeFactory;
import com.mort.easyllm.workflow.Node.runnableNode.branchNode.BranchRunnableNode;
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

    //NodeName和InfoNode的映射
    private final Map<String, InfoNode> nextNodeMap = new HashMap<>();

    private final String defaultNodeName;

    //执行单元
    private final BranchRunnableNode branchRunnableNode;

    //用于多线程中指定下一节点
    private final ConcurrentHashMap<Thread, InfoNode> nextNodes = new ConcurrentHashMap<>();


    @Builder(builderMethodName = "branchInfoNodeBuilder")
    public BranchInfoNode(@NonNull String nodeName,
                          @NonNull String nodeType,
                          @NonNull String defaultNodeName,
                          JSONObject properties,
                          Boolean isBranchNode) {
        super(nodeName, nodeType, isBranchNode, properties);
        this.branchRunnableNode = NodeFactory.getNodeByName(nodeType, properties, BranchRunnableNode.class);
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
        InfoNode nextNode = this.branchRunnableNode.run(input, nextNodeMap);
        if (nextNode == null) {
            nextNode = this.nextNodeMap.get(this.defaultNodeName);
        }
        this.setNextNode(nextNode);
        log.info("判断节点：{}，下一节点：{}", this.getNodeName(), this.getNextNode().getNodeName());
        return input;
    }

}
