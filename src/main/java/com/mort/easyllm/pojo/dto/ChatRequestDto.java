package com.mort.easyllm.pojo.dto;

import lombok.Data;

import java.util.List;

/**
 * @author Mort
 */
@Data
public class ChatRequestDto {

    private String message;

    private List<String> messages;


}
