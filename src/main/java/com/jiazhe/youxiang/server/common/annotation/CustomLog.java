/*
 * Copyright (c) 2017 ue-link.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.common.annotation;

import com.jiazhe.youxiang.server.common.enums.LogLevelEnum;
import com.jiazhe.youxiang.server.common.enums.ModuleEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/10/18
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomLog {

    /**
     * 模块名称
     */
    ModuleEnum moduleName() default ModuleEnum.LOG;

    /**
     * 操作
     */
    String operate() default "";

    /**
     * 日志级别
     */
    LogLevelEnum level() default LogLevelEnum.LEVEL_1;
}