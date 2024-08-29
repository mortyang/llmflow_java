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
        String textTemp = message;
        SessionContext.putGlobalVariable("userInput", "", textTemp);
        SessionContext.getHistoryMessages().add(Message.builder()
                .text(message)
                .role(Message.Role.user.getRoleName())
                .build());
        while (true) {
            log.info("节点开始执行：{},input:{}", nodeNow.getNodeName(), textTemp);
            textTemp = nodeNow.runNode(textTemp, null);
            log.info("节点执行结束：{},output:{}", nodeNow.getNodeName(), textTemp);
            if (nodeNow.getNextNode() == null) {
                break;
            }
            SessionContext.putGlobalVariable(nodeNow.getNodeName(), "", textTemp);
            nodeNow = nodeNow.getNextNode();
        }
        SessionContext.getHistoryMessages().add(Message.builder()
                .text(textTemp)
                .role(Message.Role.assist.getRoleName())
                .build());
        return textTemp;
    }

}
