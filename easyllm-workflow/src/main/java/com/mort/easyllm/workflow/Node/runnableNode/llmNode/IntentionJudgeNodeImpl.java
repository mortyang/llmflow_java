package com.mort.easyllm.workflow.Node.runnableNode.llmNode;

import com.alibaba.fastjson2.JSONObject;
import com.mort.easyllm.common.annotation.node.Node;
import com.mort.easyllm.common.annotation.node.PropertiesInject;
import com.mort.easyllm.common.parameter.Message;
import com.mort.easyllm.llm.tongyi.impl.Tongyi;
import com.mort.easyllm.workflow.Node.runnableNode.llmNode.properties.IntentionJudgeProperties;
import com.mort.easyllm.workflow.Node.runnableNode.NormalRunnableNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mort
 */
@Node(nodeType = "IntentionJudgeNode")
public class IntentionJudgeNodeImpl implements NormalRunnableNode {

    private final IntentionJudgeProperties properties;

    private final String sysMsg;

    @PropertiesInject
    public IntentionJudgeNodeImpl(IntentionJudgeProperties properties) {
        this.properties = properties;
        this.sysMsg = generateSysMsg(this.properties.getIntentions());
        throw new RuntimeException("12");
    }

    private String generateSysMsg(List<String> intensions) {
        StringBuilder str = new StringBuilder();
        str.append("请判断下面的消息符合下面哪种意图，可能的意图如下：");
        for (String intension : intensions) {
            str.append(intension);
            str.append(",");
        }
        str.append("若有符合则返回对应意图，若均不符合以上意图必须返回：无匹配");
        System.out.println(str);
        return str.toString();
    }

    /**
     * @param input 节点输入
     * @return 节点输出
     */
    @Override
    public String run(String input) {
        Tongyi tongyi = new Tongyi();
        List<Message> list = new ArrayList<>();
        list.add(Message.builder().role("system").text(sysMsg).build()) ;
        list.add(Message.builder().role("user").text(input).build()) ;
        return tongyi.fullSession(properties.getTongyiProperties(),list);
    }

}
