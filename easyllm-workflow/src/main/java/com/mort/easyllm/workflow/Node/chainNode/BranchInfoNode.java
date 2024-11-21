package com.mort.easyllm.workflow.Node.chainNode;

import com.alibaba.fastjson2.JSONObject;
import com.mort.easyllm.workflow.Node.runnableNode.NodeFactory;
import com.mort.easyllm.workflow.Node.runnableNode.branchNode.BranchRunnableNode;
import io.reactivex.functions.Consumer;
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

    //用于将每个线程与其对应的下一个 InfoNode 关联起来。该映射用于跟踪本节点不同线程在执行过程中生成的下一个节点。
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
    public final void runNode( Consumer<String> callback,boolean isEndNode,boolean... debugMode) {
        InfoNode nextNode = this.branchRunnableNode.run(nextNodeMap);
        if (nextNode == null) {
            nextNode = this.nextNodeMap.get(this.defaultNodeName);
        }
        this.setNextNode(nextNode);
        log.info("判断节点：{}，下一节点：{}", this.getNodeName(), this.getNextNode().getNodeName());
    }

}
