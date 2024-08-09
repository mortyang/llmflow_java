package com.mort.easyllm.enums;

import lombok.Getter;

@Getter
public enum StatusEnum {


    SUCCESS("SUCCESS","处理成功"),

    PARAMETER_ERROR("PARAMETER_ERROR","参数异常"),

    ERROT("ERROT","出现错误");

    private final String message;

    private final String code;

    StatusEnum(String i, String message) {
        this.code = i;
        this.message = message;
    }
}
