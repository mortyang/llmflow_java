package com.mort.easyllm.Node.RunableNode.RequestNode;

import com.alibaba.fastjson2.JSONObject;
import com.mort.easyllm.Annotation.Node;
import com.mort.easyllm.Node.RunableNode.RunnableNode;
import com.mort.easyllm.Node.RunableNode.RequestNode.Properties.HttpNodeProperties;

@Node(NodeName = "HttpNode")
public class HttpNodeImpl implements RunnableNode {

    private final HttpNodeProperties properties;

    public HttpNodeImpl(JSONObject properties) {
        this.properties = HttpNodeProperties.jsonObjectConvert(properties);
    }

    @Override
    public String run(String input) {

        return "";
    }

}
