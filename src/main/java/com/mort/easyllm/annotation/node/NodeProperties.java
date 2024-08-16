package com.mort.easyllm.annotation.node;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标注对应node的properties文件，nodename与@Node注解一致，
 * 用于nodeFactory生成前端返回文件
 * @Author Mort
 * @Date 2024-07-23
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface NodeProperties {
    String nodeType();
}
