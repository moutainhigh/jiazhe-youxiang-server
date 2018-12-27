/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.base.advice;

import com.google.common.collect.Maps;
import com.jiazhe.youxiang.base.util.IpAdrressUtil;
import com.jiazhe.youxiang.server.biz.SysLogBiz;
import com.jiazhe.youxiang.server.common.annotation.CustomLog;
import com.jiazhe.youxiang.server.dto.customer.CustomerDTO;
import com.jiazhe.youxiang.server.dto.sysuser.SysUserDTO;
import com.jiazhe.youxiang.server.vo.BaseVO;
import io.micrometer.core.instrument.Metrics;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.logging.log4j.util.Strings;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/9/30
 */
@Component
@Aspect
public class ControllerAdvice {
    private static final Logger LOGGER = LoggerFactory.getLogger("Out_Request");

    private static final String PREFIX_HTTP_API = "http_api_req_count";

    private static final String CODE_SUCCEED = "0";

    private static final String CODE_NOT_SUCCEED = "1";

    @Value("${log.level}")
    private Integer logLevel;

    @Pointcut("execution(* com.jiazhe.youxiang.server.controller..*(..))")
    private void anyMethod() {
    }

    @Around("anyMethod()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Boolean isSuccess = true;
        long start = System.currentTimeMillis();
        Object retVal = null;
        BindingResult bindingResult = null;
        StringBuilder methodName = new StringBuilder();
        StringBuilder argsSB = new StringBuilder();
        methodName.append(joinPoint.getTarget().getClass().getCanonicalName())
                .append(".").append(joinPoint.getSignature().getName()).append("()");
        try {
            Object[] args = joinPoint.getArgs();
            if (null != args) {
                for (int i = 0; i < args.length; i++) {
                    Object obj = args[i];
                    if (obj instanceof BindingResult) {
                        // 结果验证参数,不需要打印
                        bindingResult = (BindingResult) obj;
                    } else if (obj instanceof HttpServletRequest) {
                        //methodSB.append("\n").append(obj.toString().trim());
                    } else if (obj instanceof HttpServletResponse) {
                        //methodSB.append("\n").append(obj.toString().trim());
                    } else {
                        if (obj != null) {
                            argsSB.append(customToString(obj).toString()).append("");
                        }
                    }
                }
            }
            if (Strings.isBlank(argsSB.toString())) {
                argsSB.append("null");
            }
            LOGGER.info("Begin HTTP调用{}方法,入参:{}", methodName, argsSB.toString());
            retVal = joinPoint.proceed();
        } catch (Exception e) {
            isSuccess = false;
            long expendTime = System.currentTimeMillis() - start;
            Metrics.counter(PREFIX_HTTP_API, "method", methodName.toString(), "code", CODE_NOT_SUCCEED, "duration", String.valueOf(expendTime)).increment();
            LOGGER.info("Exception HTTP调用{}方法发生问题,入参:{}，message:{},stack:{}", methodName, argsSB.toString(), e.getMessage(), e.fillInStackTrace());
            throw e;
        } finally {
            if (isSuccess) {
                long expendTime = System.currentTimeMillis() - start;
                String retValInString = retVal != null ? customToString(retVal) : "null";
                LOGGER.info("End HTTP调用{}方法成功,入参:{}，返回值:{},耗时:{}ms", methodName, argsSB.toString(), retValInString, expendTime);
                insertLog(joinPoint, getDetail(methodName, argsSB.toString()));
                Metrics.counter(PREFIX_HTTP_API, "method", methodName.toString(), "code", CODE_SUCCEED, "duration", String.valueOf(expendTime)).increment();
            }
        }
        return retVal;
    }


    /**
     * 记录日志
     *
     * @param joinPoint
     * @param detail
     */
    private void insertLog(ProceedingJoinPoint joinPoint, String detail) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        if (method != null) {
            CustomLog customLog = method.getAnnotation(CustomLog.class);
            if (customLog != null && customLog.level().getId() >= logLevel) {

                Integer operatorId;
                String operatorName;
                if (SecurityUtils.getSubject().getPrincipal() == null) {
                    operatorId = 0;
                    operatorName = "未登录";
                } else if (SecurityUtils.getSubject().getPrincipal() instanceof SysUserDTO) {
                    SysUserDTO userDTO = (SysUserDTO) SecurityUtils.getSubject().getPrincipal();
                    operatorId = userDTO.getId();
                    operatorName = userDTO.getLoginName();
                } else if (SecurityUtils.getSubject().getPrincipal() instanceof CustomerDTO) {
                    CustomerDTO customerDTO = (CustomerDTO) SecurityUtils.getSubject().getPrincipal();
                    operatorId = customerDTO.getId();
                    operatorName = customerDTO.getMobile();
                } else {
                    operatorId = 0;
                    operatorName = "未知";
                }

                //异步插入数据库日志记录
                SysLogBiz.insert(customLog.moduleName().getName(), customLog.operate(), customLog.level().getId(), operatorId, operatorName, IpAdrressUtil.getIpAdrress(request), detail);
            }
        }
    }

    private String getDetail(StringBuilder methodName, String arg) {
        return "方法名:" + methodName + ",入参:" + arg;
    }

    /**
     * 使用java.lang.reflect进行转换
     *
     * @param object
     * @return map
     */
    private static Map<String, Object> objToMap(Object object) {
        Map<String, Object> map = Maps.newHashMap();
        if (object != null) {
            try {
                Field[] declaredFields = object.getClass().getDeclaredFields();
                for (Field field : declaredFields) {
                    field.setAccessible(true);
                    try {
                        map.put(field.getName(), field.get(object));
                    } catch (IllegalArgumentException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            } catch (SecurityException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    /**
     * 优先使用继承至BaseReq\BaseValidatedReq(入参)PaginationResponse\FailResponse\Response(出参)的toString
     * 最后使用ToStringBuilder兜底
     *
     * @param vo
     * @return
     */
    private String customToString(Object vo) {
        if (vo instanceof BaseVO) {
            return vo.toString();
        }
        return ToStringBuilder.reflectionToString(vo, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}