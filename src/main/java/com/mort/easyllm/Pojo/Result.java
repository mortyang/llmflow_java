package com.mort.easyllm.Pojo;

import lombok.Data;

@Data
public class Result<T> {

    private int code;

    private String message;

    private T data;

}
