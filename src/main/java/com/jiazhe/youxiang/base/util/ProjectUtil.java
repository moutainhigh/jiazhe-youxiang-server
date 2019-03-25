package com.jiazhe.youxiang.base.util;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

/**
 * @author TU
 * @date 2019-03-07
 */
public class ProjectUtil {

    /**
     * 判断是否是页面跳转，不是页面跳转全部视为ajax请求
     */
    public static boolean isPageJump(ServletRequest request) {
        String accept = ((HttpServletRequest) request).getHeader("Accept");
        if (null != accept && accept.contains("text/html")) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
