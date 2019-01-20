/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.dto.wechatpublic;

import com.jiazhe.youxiang.server.vo.BaseObject;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2019/1/9
 */
public class WeChatAPIResultDTO extends BaseObject {
    private static final long serialVersionUID = -2255427022086158467L;

    /***
     * {"errcode":0,"errmsg":"ok","ticket":"kgt8ON7yVITDhtdwci0qeYgfghPFLLyaFEMEilYWwzuI4F2jKOXtLYkL1FlMX96QIqsKmuwW3ra2_7m2dyW3lQ","expires_in":7200}
     */

    /**
     * 错误码 0为正确
     */
    private Integer errcode;
    /**
     * 错误信息
     */
    private String errmsg;
    /**
     * jsapi_ticket
     */
    private String ticket;
    /**
     * access_token
     */
    private String accessToken;

    /**
     * 过期时间
     */
    private Integer expiresIn;

    public Integer getErrcode() {
        return errcode;
    }

    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }
}