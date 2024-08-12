package com.mort.easyllm.Node.runnableNode.llmNode;

import com.alibaba.fastjson2.JSONObject;
import com.mort.easyllm.annotation.node.Node;
import com.mort.easyllm.config.TongyiConfig;
import com.mort.easyllm.Node.runnableNode.llmNode.properties.IntentionJudgeProperties;
import com.mort.easyllm.Node.runnableNode.llmNode.utils.TongyiUtil;
import com.mort.easyllm.Node.runnableNode.RunnableNode;

import java.util.List;

/**
 * @author Mort
 */
@Node(nodeType = "IntentionJudgeNode")
public class IntentionJudgeNodeImpl implements RunnableNode {

    private final IntentionJudgeProperties properties;

    private final String sysMsg;

    private final TongyiConfig tongyiConfig;

    public IntentionJudgeNodeImpl(JSONObject properties) {
        this.properties = IntentionJudgeProperties.jsonObjectConvert(properties);
        this.tongyiConfig = new TongyiConfig(this.properties.getModelName());
        this.sysMsg = generateSysMsg(this.properties.getIntentions());
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
        // TODO 支持不同的模型平台
        return TongyiUtil.createFullSession(input, this.tongyiConfig, sysMsg);
    }

}
