package com.mort.easyllm.Annotation.Node;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标注类是一个node，nodeFactory会将node扫描注册用于后续构建workflow
 * @Author Mort
 * @Date 2024-07-23
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Node {
    String nodeName();
}