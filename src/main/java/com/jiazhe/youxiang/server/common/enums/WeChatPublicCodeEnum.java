/*
 * Copyright (c) 2017 ue-link.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.common.enums;

/**
 * 微信公众号相关返回码
 *
 * @author niexiao
 * @created 2019/1/8
 */
public enum WeChatPublicCodeEnum {

    CLIENT_KEY_ERROR(118001, "CLIENT_KEY_ERROR", "客户端唯一标识错误"),
    TIMESTAMP_IS_NULL(118002, "TIMESTAMP_IS_NULL", "生成签名的时间戳不能为空"),
    NONCE_STR_IS_NULL(118003, "NONCE_STR_IS_NULL", "生成签名的随机串不能为空"),
    URL_IS_NULL(118004, "URL_IS_NULL", "当前页面的url不能为空，且必须是完整的url"),
    GET_ACCESS_TOKEN_ERROR(118005, "GET_ACCESS_TOKEN_ERROR", "获取access_token失败"),
    GET_JSAPI_TICKET_ERROR(118006, "GET_JSAPI_TICKET_ERROR", "获取jsapi_ticket失败"),
    SIGNATURE_IS_NULL(118007, "SIGNATURE_IS_NULL", "签名不能为空"),
    ECHOSTR_IS_NULL(118008, "ECHOSTR_IS_NULL", "随机字符串不能为空"),

    ;

    WeChatPublicCodeEnum(Integer code, String type, String message) {
        this.code = code;
        this.type = type;
        this.message = message;
    }

    private final Integer code;
    private final String type;
    private final String message;

    public Integer getCode() {
        return code;
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }
}
