/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.advice;

import com.google.common.collect.Maps;
import com.jiazhe.youxiang.server.vo.BaseVO;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.logging.log4j.util.Strings;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
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
            LOGGER.info("Exception HTTP调用{}方法发生问题,入参:{}，message:{},stack:{}", methodName, argsSB.toString(), e.getMessage(), e.fillInStackTrace());
            throw e;
        } finally {
            if (isSuccess) {
                long end = System.currentTimeMillis();
                long expendTime = end - start;
                String retValInString = retVal != null ? customToString(retVal) : "null";
                LOGGER.info("End HTTP调用{}方法成功,入参:{}，返回值:{},耗时:{}ms", methodName, argsSB.toString(), retValInString, expendTime);
            }
        }
        return retVal;
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