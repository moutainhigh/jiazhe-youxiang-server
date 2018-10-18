/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.base.config;

import com.jiazhe.youxiang.base.handler.CustomLogHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/10/15
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/statics/**").addResourceLocations("classpath:/statics/");
        // 解决 SWAGGER 404报错
        registry.addResourceHandler("/swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册拦截器
        InterceptorRegistration ir = registry.addInterceptor(new CustomLogHandler());
        // 配置拦截的路径
        ir.addPathPatterns("/api/**");
        // 配置不拦截的路径
        ir.excludePathPatterns("/static/**");
        ir.excludePathPatterns("/**.html");

        // 还可以在这里注册其它的拦截器
        //registry.addInterceptor(new OtherInterceptor()).addPathPatterns("/**");
    }
}