package com.mort.easyllm.Node.InfoNode;

import com.alibaba.fastjson2.JSONObject;
import com.mort.easyllm.Utils.NodeFactory;
import com.mort.easyllm.Node.RunableNode.RunnableNode;
import lombok.*;
import org.jetbrains.annotations.NotNull;

/**
 * @author Mort
 */
@Getter
@ToString
public class InfoNode {

    @NonNull
    private final String nodeName;
    @NonNull
    private final String nodeType;
    @NonNull
    private final Boolean isBranchNode;
    // 执行单元
    private final RunnableNode runnableNode;

    private InfoNode nextNode;

    @Builder
    public InfoNode(@NonNull String nodeName,
                    @NonNull String nodeType,
                    @NotNull Boolean isBranchNode,
                    JSONObject properties) {
        this.nodeName = nodeName;
        this.nodeType = nodeType;
        this.isBranchNode = isBranchNode;
        if (!isBranchNode) {
            this.runnableNode = NodeFactory.getNodeByName(nodeType, properties, RunnableNode.class);
            return;
        }
        this.runnableNode = null;
    }

    public void setNextNode(InfoNode infoNode) {
        if (this.nextNode != null) {
            throw new RuntimeException("NextNode不可修改");
        }
        this.nextNode = infoNode;
    }


    public String runNode(String input) {
        return this.runnableNode.run(input);
    }


}
