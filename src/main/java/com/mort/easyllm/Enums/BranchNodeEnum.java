package com.mort.easyllm.Enums;


import com.mort.easyllm.Node.RunableNode.RequestNode.HttpNodeImpl;

public enum BranchNodeEnum {

    HttpNode("HttpNode", HttpNodeImpl.class);


    private final String message;

    private final Class<?> typeReference;

    BranchNodeEnum(String message,Class<?> typeReference) {
        this.typeReference = typeReference;
        this.message = message;
    }

}
