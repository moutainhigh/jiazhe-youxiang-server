/*
 * Copyright (c) 2019 橙谊科技
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.vo.req.djbx;

import com.jiazhe.youxiang.server.common.constant.DJBXConstant;
import com.jiazhe.youxiang.server.vo.BaseVO;

/**
 * @author tu
 * @version 1.0
 * @description TODO
 * @created 2020-05-19 21:03
 */
public class PointsTokenReq extends BaseVO {

    private String appName;

    private String userName;

    private String serect;

    public PointsTokenReq(String serect) {
        this.appName = DJBXConstant.APP_NAME;
        this.userName = DJBXConstant.USER_NAME;
        this.serect = serect;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSerect() {
        return serect;
    }

    public void setSerect(String serect) {
        this.serect = serect;
    }
}
