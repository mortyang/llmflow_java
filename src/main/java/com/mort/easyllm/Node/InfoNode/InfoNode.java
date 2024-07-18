package com.mort.easyllm.Node.InfoNode;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONPObject;
import com.mort.easyllm.Config.NodeFactory;
import com.mort.easyllm.Node.RunableNode.RunnableNode;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;

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


    public InfoNode() {}

    @Builder
    public InfoNode(@NonNull String nodeName,
                    @NonNull String nodeType,
                    @NotNull Boolean isBranchNode,
                    JSONObject properties,
                    InfoNode nextNode) {
        this.nodeName = nodeName;
        this.nodeType = nodeType;
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
