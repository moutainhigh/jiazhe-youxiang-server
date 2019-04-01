/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.base.util;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.util.Strings;

import javax.servlet.http.HttpServletRequest;

/**
 * 获取Ip地址工具类
 *
 * @author niexiao
 * @created 2018/10/18
 */
public class IpAdrressUtil {

    /**
     * 判断登陆登陆ip是否在白名单内
     *
     * @param whiteIps
     * @param loginIp
     * @return
     */
    public static boolean ipIsWhite(String loginIp, String whiteIps) {
        if (Strings.isEmpty(whiteIps) || Strings.isEmpty(loginIp)) {
            return false;
        }
        String[] whiteIpsArr = whiteIps.split(";");
        for (String ip : whiteIpsArr) {
            if (ip.equals(loginIp)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取Ip地址
     *
     * @param request
     * @return
     */
    public static String getIpAddress(HttpServletRequest request) {
        String Xip = request.getHeader("X-Real-IP");
        String XFor = request.getHeader("X-Forwarded-For");
        if (StringUtils.isNotEmpty(XFor) && !"unKnown".equalsIgnoreCase(XFor)) {
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = XFor.indexOf(",");
            if (index != -1) {
                return XFor.substring(0, index);
            } else {
                return XFor;
            }
        }
        XFor = Xip;
        if (StringUtils.isNotEmpty(XFor) && !"unKnown".equalsIgnoreCase(XFor)) {
            return XFor;
        }
        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("HTTP_CLIENT_IP");
        }
        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (StringUtils.isBlank(XFor) || "unknown".equalsIgnoreCase(XFor)) {
            XFor = request.getRemoteAddr();
        }
        return XFor;
    }
}