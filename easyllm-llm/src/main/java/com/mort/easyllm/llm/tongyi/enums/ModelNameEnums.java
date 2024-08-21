package com.mort.easyllm.llm.tongyi.enums;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Getter
public enum ModelNameEnums {

    QWEN_MAX("qwen-max"),
    QWEN_PLUS("qwen-plus"),
    QWEN_TURBO("qwen-turbo");

    private final String value;
    private static final Map<String, ModelNameEnums> VALUE_MAP = new HashMap<>();

    static {
        for (ModelNameEnums model : values()) {
            VALUE_MAP.put(model.value, model);
        }
    }

    ModelNameEnums(String value) {
        this.value = value;
    }

    public static ModelNameEnums fromValue(String value) {
        ModelNameEnums model = VALUE_MAP.get(value);
        if (model == null) {
            throw new IllegalArgumentException("No enum constant for value:" + value);
        }
        return model;
    }
}