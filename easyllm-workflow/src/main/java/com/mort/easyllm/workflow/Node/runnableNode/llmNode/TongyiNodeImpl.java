package com.mort.easyllm.workflow.Node.runnableNode.llmNode;

import com.alibaba.fastjson2.JSONObject;
import com.mort.easyllm.common.annotation.node.Node;
import com.mort.easyllm.common.annotation.node.PropertiesInject;
import com.mort.easyllm.common.parameter.Message;
import com.mort.easyllm.llm.tongyi.impl.Tongyi;
import com.mort.easyllm.workflow.Node.runnableNode.llmNode.properties.TongyiNodeProperties;
import com.mort.easyllm.workflow.Node.runnableNode.NormalRunnableNode;
import com.mort.easyllm.workflow.context.SessionContext;

import java.util.ArrayList;
import java.util.List;

@Node(nodeType = "TongyiNode")
public class TongyiNodeImpl implements NormalRunnableNode {

    @PropertiesInject
    private TongyiNodeProperties properties;


    @Override
    public String run(String input) {
        Tongyi tongyi = new Tongyi();
        List<Message> messages = new ArrayList<>();
        if(properties.getSysMsg()!=null){
            messages.add(Message.builder().role("system").text(properties.getSysMsg()).build());
        }
        if(properties.getNeedSessionContext() == 1){
            messages.addAll(SessionContext.getSessionContext());
        }else{
            messages.add(Message.builder().role("user").text(input).build());
        }
        return tongyi.fullSession(properties.getTongyiProperties(),messages);
    }

}
