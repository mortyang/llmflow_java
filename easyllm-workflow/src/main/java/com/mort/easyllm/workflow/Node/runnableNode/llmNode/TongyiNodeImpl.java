package com.mort.easyllm.workflow.Node.runnableNode.llmNode;

import com.mort.easyllm.workflow.Node.chainNode.InfoNode;
import com.mort.easyllm.workflow.annotation.node.Node;
import com.mort.easyllm.workflow.annotation.node.PropertiesInject;
import com.mort.easyllm.workflow.Node.runnableNode.llmNode.properties.TongyiNodeProperties;
import com.mort.easyllm.workflow.Node.runnableNode.NormalRunnableNode;
import com.mort.easyllm.common.context.SessionContext;
import com.mort.easyllm.common.parameter.Message;
import com.mort.easyllm.llm.supplier.tongyi.impl.Tongyi;
import io.reactivex.functions.Consumer;

import java.util.ArrayList;
import java.util.List;

@Node(nodeType = "TongyiNode")
public class TongyiNodeImpl implements NormalRunnableNode {


    @PropertiesInject
    private TongyiNodeProperties properties;


    @Override
    public String run(InfoNode infoNode, Consumer<String> callback) {
        Tongyi tongyi = new Tongyi();
        List<Message> messages = new ArrayList<>();
        if (properties.getSysMsg() != null) {
            messages.add(Message.builder().role("system").text(properties.getSysMsg().getString()).build());
        }
        if (properties.getLlmProperties().getUseContext()) {
            messages.addAll(SessionContext.getHistoryMessages());
        }
        messages.add(Message.builder().role("user").text(properties.getInput().getString()).build());
        return tongyi.fullSession(properties.getLlmProperties(), messages);
    }

}
