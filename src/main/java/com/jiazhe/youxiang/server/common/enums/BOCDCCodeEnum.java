/*
 * Copyright (c) 2017 ue-link.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.common.enums;

/**
 * 中行储蓄卡
 *
 * @author niexiao
 * @created 2018/10/31
 */
public enum BOCDCCodeEnum {

    REQUEST_MESSAGE_ERROR(122001, "REQUEST_MESSAGE_ERROR", "请求报文有误"),
    STATUS_CHECK_ERROR(122002, "STATUS_CHECK_ERROR", "使用状态核对实时接口调用失败"),
    ;

    BOCDCCodeEnum(Integer code, String type, String message) {
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