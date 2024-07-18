package com.mort.easyllm.Node.RunableNode.RequestNode;

import com.alibaba.fastjson2.JSONObject;
import com.mort.easyllm.Node.RunableNode.RunnableNode;
import com.mort.easyllm.Node.RunableNode.RequestNode.Properties.HttpNodeProperties;

public class HttpNodeImpl implements RunnableNode {

    private final HttpNodeProperties properties;

    public HttpNodeImpl(Object properties) {
        this.properties = HttpNodeProperties.jsonObjectConvert((JSONObject) properties);
        System.out.println("HttpNode properties:" + properties);
    }

    @Override
    public String run(String input) {
        System.out.println("HttpNode properties:" + properties);
        return "";
    }

}
