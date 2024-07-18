package com.mort.easyllm.Node.RunableNode.RequestNode;

import com.fasterxml.jackson.databind.JsonNode;
import com.mort.easyllm.Node.RunableNode.RunnableNode;
import com.mort.easyllm.Node.RunableNode.RequestNode.Properties.HttpNodeProperties;

public class HttpNodeImpl implements RunnableNode {

    private final HttpNodeProperties properties;

    public HttpNodeImpl(Object properties) {
        this.properties = HttpNodeProperties.jsonObjectConvert((JsonNode) properties);
        System.out.println("HttpNode properties:" + properties);
    }

    @Override
    public String run(String input) {
        System.out.println("HttpNode properties:" + properties);
        return "";
    }

}
