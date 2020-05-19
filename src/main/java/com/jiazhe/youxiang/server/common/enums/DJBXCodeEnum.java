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
public enum DJBXCodeEnum {
    APPVALUE_ERROR(125001, "APPVALUE_ERROR", "appValue不合法"),
    CODE_IS_NULL(125002, "CODE_IS_NULL", "code不能为空"),
    GET_USER_INFO_ERROR(125003, "GET_USER_INFO_ERROR", "根据code获取用户信息失败"),
    TOKEN_INVALID(125004, "TOKEN_INVALID", "token失效"),
    GET_TOKEN_ERROR(125005, "GET_TOKEN_ERROR", "获取token失败"),;

    DJBXCodeEnum(Integer code, String type, String message) {
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
