package com.mort.easyllm.workFlow.Node.runnableNode.llmNode;

import com.alibaba.fastjson2.JSONObject;
import com.mort.easyllm.annotation.node.Node;
import com.mort.easyllm.config.TongyiConfig;
import com.mort.easyllm.workFlow.Node.runnableNode.llmNode.properties.TongyiProperties;
import com.mort.easyllm.workFlow.Node.runnableNode.llmNode.utils.TongyiUtil;
import com.mort.easyllm.workFlow.Node.runnableNode.NormalRunnableNode;

@Node(nodeType = "TongyiNode")
public class TongyiNodeImpl implements NormalRunnableNode {

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