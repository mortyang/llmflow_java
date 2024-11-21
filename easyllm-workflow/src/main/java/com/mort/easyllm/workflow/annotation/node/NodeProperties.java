package com.mort.easyllm.workflow.annotation.node;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于指定节点的配置类，同一节点的nodeType与Node注解一致否则无法正确找到并注入配置类
 * @author Mort
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface NodeProperties {
    String nodeType();
}
