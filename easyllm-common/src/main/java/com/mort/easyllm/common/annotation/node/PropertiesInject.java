package com.mort.easyllm.common.annotation.node;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于标注节点配置类注入的入口
 * ，可以用于标注字段或是构造函数
 * @Author Mort
 * @Date 2024-07-23
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.CONSTRUCTOR})
public @interface PropertiesInject {
}
