/*
 * Copyright (c) 2019 橙谊科技
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.common.constant;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author tu
 * @version 1.0
 * @description 根据环境加载的静态变量
 * @created 2019-09-12 8:06
 */
@Configuration
public class EnvironmentConstant {

    /**
     * 环境
     */
    public static String ENVIRONMENT;

    @Value("${spring.profiles.active}")
    public void setEnvironment(String Environment) {
        ENVIRONMENT = Environment;
    }


}
