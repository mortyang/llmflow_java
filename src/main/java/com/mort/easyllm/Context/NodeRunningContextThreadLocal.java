package com.mort.easyllm.Context;

/**
 * node执行全局上下文，存储每个节点产生的数据与历史数据
 * @Author Mort
 * @Date 2024-07-23
 */
public class NodeRunningContextThreadLocal {

    private static final ThreadLocal<NodeRunning> nodeContext = ThreadLocal.withInitial(NodeRunning::new);

    public static NodeRunning getNodecontext(){
        return nodeContext.get();
    }

    public static void removeNodecontext(){
        nodeContext.remove();
    }
}
