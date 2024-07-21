package com.mort.easyllm.Utils;

import com.mort.easyllm.Node.InfoNode.InfoNode;

import java.util.concurrent.ConcurrentHashMap;


// 存储本实例目前启用的流程
public class UsingWorkFlowMap {

    public static final ConcurrentHashMap<String, InfoNode> RUNNING_WORK_FLOW_MAP = new ConcurrentHashMap<>();

}
