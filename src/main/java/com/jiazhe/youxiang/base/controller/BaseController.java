package com.jiazhe.youxiang.base.controller;

import com.jiazhe.youxiang.base.util.ProjectUtil;
import com.jiazhe.youxiang.base.util.ResponseUtil;
import com.jiazhe.youxiang.base.util.ResultPackage;
import net.sf.json.JSONObject;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by TU on 2018/8/31.
 * 处理无权限状态下的ajax请求，或页面跳转请求的异常处理【跳转到登403页面】
 */
public abstract class BaseController {
    /**
     * 权限异常
     */
    @ExceptionHandler({ UnauthorizedException.class, AuthorizationException.class })
    public String authorizationException(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (ProjectUtil.isAjax(request)) {
            String code = "000002";
            String msg = "无权限";
            System.out.println("BaseController：ajax请求，无权限");
            ResponseUtil.responseUtils(response, ResultPackage.resultPackage(code,new JSONObject(),msg));
            return null;
        } else {
            System.out.println("BaseController：页面跳转，无权限");
            return "redirect:../system/403";
        }
    }
}
