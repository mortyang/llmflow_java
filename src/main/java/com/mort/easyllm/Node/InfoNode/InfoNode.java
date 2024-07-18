package com.mort.easyllm.Node.InfoNode;

import com.mort.easyllm.Config.NodeFactory;
import com.mort.easyllm.Node.RunableNode.RunnableNode;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

/**
 * @author Mort
 */
@Data
public class InfoNode {

    @NonNull
    private String nodeName;
    @NonNull
    private String nodeType;
    @NonNull
    private Boolean isBranchNode;

    private InfoNode nextNode;
    // 执行单元
    private RunnableNode runnableNode;

    private String input;
    private String output;


    @Builder
    public InfoNode(@NonNull String nodeName,
                    @NonNull String nodeType,
                    @NonNull String input,
                    Object properties,
                    InfoNode nextNode, boolean isBranchNode) {
        this.nodeName = nodeName;
        this.nodeType = nodeType;
        this.input = input;
        this.nextNode = nextNode;
        this.isBranchNode = isBranchNode;
        if (!isBranchNode) {
            this.runnableNode = (RunnableNode) NodeFactory.getRunableNodeByName(nodeType, properties);
        }
    }


    public void runNode() {
        this.output = this.runnableNode.run(this.input);
        if (nextNode != null) {
            this.nextNode.input = this.output;
        }
    }


}
