package com.jiazhe.youxiang.base.util;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by TU on 2018/9/1.
 */
public class ProjectUtil {

    /**
     * 判断是是否是ajax类型
     */
    public static boolean isAjax(ServletRequest request){
        String accept = ((HttpServletRequest) request).getHeader("Accept");
        System.out.println(accept);
        if(accept.contains("text/html")){
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
}
