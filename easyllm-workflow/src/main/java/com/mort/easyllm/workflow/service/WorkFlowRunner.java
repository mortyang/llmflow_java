package com.mort.easyllm.workflow.service;

import com.mort.easyllm.common.context.SessionContext;
import com.mort.easyllm.common.parameter.Message;
import com.mort.easyllm.workflow.Node.chainNode.InfoNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class WorkFlowRunner {


    public String runWorkFlow(InfoNode startNode, String message) {
        InfoNode nodeNow = startNode;
        SessionContext.putTemporaryVariable(message, "startNode");
        SessionContext.getHistoryMessages().add(Message.builder()
                .text(message)
                .role(Message.Role.user.getRoleName())
                .build());
        while (true) {
            log.info("节点开始执行：{}", nodeNow.getNodeName());
            if (nodeNow.getNextNode() == null && !nodeNow.getIsBranchNode()) {
                nodeNow.runNode(s -> {},true);
                break;
            }
            nodeNow.runNode(s -> {},false);
            nodeNow = nodeNow.getNextNode();
        }

        SessionContext.getHistoryMessages().add(Message.builder()
                .text(SessionContext.getVariableByName("endNode"))
                .role(Message.Role.assist.getRoleName())
                .build());
        return SessionContext.getVariableByName("endNode");
    }

}
