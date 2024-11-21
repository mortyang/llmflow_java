package com.mort.easyllm.main.pojo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Result<T> {

    private String code;

    private String message;

    private T data;

}
