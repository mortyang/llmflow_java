package com.mort.easyllm.main.pojo.dto;

import lombok.Data;

import java.util.List;

/**
 * @author Mort
 */
@Data
public class ChatRequestDto {

    private String message;

    private String sessionId;

}
