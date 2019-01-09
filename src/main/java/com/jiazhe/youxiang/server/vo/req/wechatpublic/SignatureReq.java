/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.vo.req.wechatpublic;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

/**
 * 签名req
 *
 * @author niexiao
 * @created 2019/1/8
 */
public class SignatureReq extends BaseVO {

    private static final long serialVersionUID = 8271753093295580166L;
    @ApiModelProperty("必填，客户端唯一标识，由服务端提供，一旦确定不再改变")
    private String clientKey;
    @ApiModelProperty("必填，生成签名的时间戳")
    private String timestamp;
    @ApiModelProperty("必填，生成签名的随机串")
    private String nonceStr;
    @ApiModelProperty("必填，当前页面的url，必须是完整的url")
    private String url;

    public String getClientKey() {
        return clientKey;
    }

    public void setClientKey(String clientKey) {
        this.clientKey = clientKey;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}