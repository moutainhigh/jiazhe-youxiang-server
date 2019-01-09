/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.vo.resp;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

/**
 * 签名Resp
 *
 * @author niexiao
 * @created 2019/1/8
 */
public class SignatureResp extends BaseVO {
    private static final long serialVersionUID = -8274751526345543268L;

    @ApiModelProperty("签名")
    private String signature;

    @ApiModelProperty("appid")
    private String appid;

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }
}