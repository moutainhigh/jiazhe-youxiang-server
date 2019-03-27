package com.jiazhe.youxiang.base.controller;

import com.jiazhe.youxiang.base.util.ProjectUtil;
import com.jiazhe.youxiang.server.common.enums.LoginCodeEnum;
import net.sf.json.JSONObject;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by TU on 2018/8/31.
 * 处理无权限状态下的ajax请求，或页面跳转请求的异常处理【跳转到登403页面】
 */
public abstract class BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    /**
     * 权限异常
     */
    @ExceptionHandler({UnauthorizedException.class, AuthorizationException.class})
    public String authorizationException(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (ProjectUtil.isPageJump(request)) {
            LOGGER.info("BaseController：页面跳转，无权限");
            return "redirect:/system/403";
        } else {
            LOGGER.info("BaseController：ajax请求，无权限");
            JSONObject obj = new JSONObject();
            obj.put("code", LoginCodeEnum.LOGIN_NO_PERMISSION.getCode());
            obj.put("type", LoginCodeEnum.LOGIN_NO_PERMISSION.getType());
            obj.put("message", LoginCodeEnum.LOGIN_NO_PERMISSION.getMessage());
            JSONObject result = new JSONObject();
            result.put("error", obj);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(result.toString());
            return null;
        }
    }
}
