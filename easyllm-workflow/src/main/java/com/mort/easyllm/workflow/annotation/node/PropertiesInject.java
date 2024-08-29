package com.mort.easyllm.workflow.annotation.node;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于标注Node节点配置类（NodeProperties）注入的入口
 * ，可以用于标注字段或是构造函数
 * @Author Mort
 * @Date 2024-07-23
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.CONSTRUCTOR})
public @interface PropertiesInject {

}
