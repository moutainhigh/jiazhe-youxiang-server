/*
 * Copyright (c) 2017 ue-link.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.vo.req.wechatpublic;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

/**
 * 签名验证req
 *
 * @author niexiao
 * @created 2019/1/8
 */
public class CheckSignatureReq extends BaseVO {

    private static final long serialVersionUID = 8271753093295580166L;
    @ApiModelProperty("必填，微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。")
    private String signature;
    @ApiModelProperty("必填，时间戳")
    private String timestamp;
    @ApiModelProperty("必填，随机数")
    private String nonce;
    @ApiModelProperty("必填，随机字符串")
    private String echostr;

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getEchostr() {
        return echostr;
    }

    public void setEchostr(String echostr) {
        this.echostr = echostr;
    }
}