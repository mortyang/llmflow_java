package com.mort.easyllm.Pojo.dto;

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
