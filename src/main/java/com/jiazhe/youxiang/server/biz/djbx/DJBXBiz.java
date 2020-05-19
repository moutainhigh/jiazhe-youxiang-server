/*
 * Copyright (c) 2019
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.biz.djbx;

import com.jiazhe.youxiang.base.util.HttpUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2020-05-19
 */
@Service
public class DJBXBiz {

    @Value("${djbx.api.getuserinfo}")
    private String DJBX_API_GETUSERINFO;

    /**
     * 企业微信外部登录
     *
     * @param appvalue
     * @param code
     */
    public void externalLogin(String appvalue, String code) {
        //TODO niexiao 调用大家保险的接口，获得代理人信息


        //TODO zhaoweixin 利用代理人信息，创建自己的用户，并使该用户在登录状态

    }
}
