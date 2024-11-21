package com.mort.easyllm.common.enums;

import lombok.Getter;

@Getter
public enum StatusEnum {


    SUCCESS("SUCCESS","处理成功"),

    PARAMETER_ERROR("PARAMETER_ERROR","参数异常"),

    NULL_NODE("NULL_NODE","不存在的Flow"),

    ERROT("ERROT","出现错误");

    private final String message;

    private final String code;

    StatusEnum(String i, String message) {
        this.code = i;
        this.message = message;
    }
}
