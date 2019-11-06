/*
 * Copyright (c) 2017 ue-link.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.common.enums;

/**
 * @author TU
 * @description 中行信用卡
 * @date 2019-09-16.
 */
public enum BOCCCCodeEnum {
    PARAM_ENCRYPT_ERROR(123001, "PARAM_ENCRYPT_ERROR", "参数加密错误"),
    USED_UPDATE_ERROR(123002, "USED_UPDATE_ERROR", "使用更新实时接口调用失败"),
    REFUND_UPDATE_ERROR(123003, "REFUND_UPDATE_ERROR", "使用更新实时接口调用失败"),
    ;

    BOCCCCodeEnum(Integer code, String type, String message) {
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