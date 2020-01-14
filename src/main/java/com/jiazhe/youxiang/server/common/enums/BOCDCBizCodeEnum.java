/*
 * Copyright (c) 2019
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.common.enums;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2019-09-11
 */
public enum BOCDCBizCodeEnum {

    SUCCESS("0000", "成功"),
    MERCHANT_RETURNS_USED("2222", "商户返回已使用"),
    MERCHANT_RETURNS_EXPIRY_DATE_ERROR("5555", "商户返回有效期不符"),
    MESSAGE_FORMAT_ERROR("9999", "报文格式错误"),
    ;

    BOCDCBizCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private final String code;
    private final String message;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
