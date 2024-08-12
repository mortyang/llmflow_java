package com.mort.easyllm.exception;


import lombok.extern.slf4j.Slf4j;

/**
 * @author Mort
 */
@Slf4j
public class PropertiesParseException extends RuntimeException {

    public PropertiesParseException(String message,String nodeName){
        log.error("流程解析异常，异常node：{}，出现的问题：{}",nodeName,message);
    }

}
