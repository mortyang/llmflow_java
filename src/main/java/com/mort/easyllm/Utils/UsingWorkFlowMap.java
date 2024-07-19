package com.mort.easyllm.Utils;

import com.mort.easyllm.Node.InfoNode.InfoNode;

import java.util.concurrent.ConcurrentHashMap;

public class UsingWorkFlowMap {

    public static final ConcurrentHashMap<String, InfoNode> RUNNING_WORK_FLOW_MAP = new ConcurrentHashMap<>();

}
