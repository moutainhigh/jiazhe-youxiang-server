/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.base.handler;

import com.jiazhe.youxiang.base.util.ProjectUtil;
import com.jiazhe.youxiang.base.util.ResponseUtil;
import com.jiazhe.youxiang.base.util.ResultPackage;
import com.jiazhe.youxiang.server.common.enums.CommonCodeEnum;
import com.jiazhe.youxiang.server.common.exceptions.CommonException;
import com.jiazhe.youxiang.server.vo.FailResponse;
import com.jiazhe.youxiang.server.vo.ResponseMsg;
import net.sf.json.JSONObject;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 异常统一处理类，返回json数据
 *
 * @author niexiao
 * @created 2018/10/17
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger("Out_Request");

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Object exceptionHandler(Exception e, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        ResponseMsg rm = new ResponseMsg();
        FailResponse resp = new FailResponse(rm);

        if (e instanceof CommonException) {
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            rm.setCode(((CommonException) e).getCode());
            rm.setMessage(((CommonException) e).getCustomMsg());
            rm.setType(((CommonException) e).getType());
            LOGGER.info("service exception:{}", e.getMessage());
        } else if (e instanceof ServletException) {
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            rm.setCode(CommonCodeEnum.PARAMS_ILLEGAL_ERROR.getCode());
            rm.setType(CommonCodeEnum.PARAMS_ILLEGAL_ERROR.getType());
            rm.setMessage(CommonCodeEnum.PARAMS_ILLEGAL_ERROR.getMessage());
            LOGGER.info("param error exception:{}", e.getMessage());
        } else if (e instanceof BindException) {
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            rm.setCode(CommonCodeEnum.PARAMS_ILLEGAL_ERROR.getCode());
            rm.setType(CommonCodeEnum.PARAMS_ILLEGAL_ERROR.getType());
            rm.setMessage(((BindException) e).getBindingResult().toString());
            LOGGER.info("param error exception:{}", e.getMessage());
        } else {
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            rm.setCode(CommonCodeEnum.INTERNAL_ERROR.getCode());
            rm.setMessage(CommonCodeEnum.INTERNAL_ERROR.getMessage());
            rm.setType(CommonCodeEnum.INTERNAL_ERROR.getType());
            LOGGER.error("occur unknown exception:{}", e.getMessage());
        }
        return resp;
    }
}