package com.mort.easyllm.Node.RunableNode.LLMNode;

import com.alibaba.fastjson2.JSONObject;
import com.mort.easyllm.Annotation.Node.Node;
import com.mort.easyllm.Config.TongyiConfig;
import com.mort.easyllm.Node.RunableNode.LLMNode.Properties.TongyiProperties;
import com.mort.easyllm.Node.RunableNode.LLMNode.Utils.TongyiUtil;
import com.mort.easyllm.Node.RunableNode.RunnableNode;

@Node(nodeName = "TongyiNode")
public class TongyiNodeImpl implements RunnableNode {

    private final TongyiProperties properties;

    private final TongyiConfig tongyiConfig;

    public TongyiNodeImpl(JSONObject properties){
        this.properties = TongyiProperties.jsonObjectConvert(properties);
        this.tongyiConfig = new TongyiConfig(this.properties.getModelName());
    }


    @Override
    public String run(String input) {
        return TongyiUtil.createFullSession(input,this.tongyiConfig,properties.getSysMsg());
    }

}
