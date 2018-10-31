/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.common.enums;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/10/31
 */
public enum CustomerCodeEnum {

    CUSTOMER_MOBILE_ERROR(109001, "CUSTOMER_MOBILE_ERROR", "客户的电话为空或者格式错误"),
    CUSTOMER_NAME_IS_NULL(109002, "CUSTOMER_NAME_IS_NULL", "客户的姓名不能为空"),
    CUSTOMER_MOBILE_REPEAT(109003, "CUSTOMER_MOBILE_REPEAT", "客户的电话号码重复");

    CustomerCodeEnum(Integer code, String type, String message) {
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