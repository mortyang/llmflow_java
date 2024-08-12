package com.mort.easyllm.Node.branchNode;

import com.mort.easyllm.Node.InfoNode.InfoNode;

import java.util.Map;

/**
 * @author Mort
 */
public interface BranchNode {

    InfoNode run(String input, Map<String,InfoNode> infoNode);

}
