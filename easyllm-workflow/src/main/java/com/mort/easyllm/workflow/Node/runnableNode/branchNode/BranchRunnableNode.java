package com.mort.easyllm.workflow.Node.runnableNode.branchNode;

import com.mort.easyllm.workflow.Node.chainNode.InfoNode;

import java.util.Map;

/**
 * @author Mort
 */
public interface BranchRunnableNode {

    InfoNode run(Map<String,InfoNode> infoNode);

}
