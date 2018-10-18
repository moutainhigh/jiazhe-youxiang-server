/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.base.handler;

import com.jiazhe.youxiang.base.util.IpAdrressUtil;
import com.jiazhe.youxiang.server.biz.SysLogBiz;
import com.jiazhe.youxiang.server.common.annotation.CustomLog;
import com.jiazhe.youxiang.server.vo.BaseVO;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/10/18
 */
@Component
public class CustomLogHandler extends HandlerInterceptorAdapter {
//    private static final ThreadLocal<Long> customThreadLocal = new ThreadLocal<>();
//
//    //前置拦截
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        if (!(handler instanceof HandlerMethod)) {
//            return super.preHandle(request, response, handler);
//        }
//        customThreadLocal.set(System.currentTimeMillis());
//        return super.preHandle(request, response, handler);
//    }
//
//    //后置拦截
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        if (!(handler instanceof HandlerMethod)) {
//            super.preHandle(request, response, handler);
//            return;
//        }
//        HandlerMethod method = (HandlerMethod) handler;
//        CustomLog customLog = method.getMethodAnnotation(CustomLog.class);
//        if (customLog != null) {
//            //异步插入数据库日志记录
//            SysLogBiz.insert(customLog.moduleName(), customLog.operate(), customLog.level().getId(), IpAdrressUtil.getIpAdrress(request));
//        }
//    }
//
//    /**
//     * 优先使用继承至BaseReq\BaseValidatedReq(入参)PaginationResponse\FailResponse\Response(出参)的toString
//     * 最后使用ToStringBuilder兜底
//     *
//     * @param vo
//     * @return
//     */
//    private String customToString(Object vo) {
//        if (vo instanceof BaseVO) {
//            return vo.toString();
//        }
//        return ToStringBuilder.reflectionToString(vo, ToStringStyle.SHORT_PREFIX_STYLE);
//    }
}