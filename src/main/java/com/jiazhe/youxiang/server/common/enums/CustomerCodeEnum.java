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
    CUSTOMER_MOBILE_REPEAT(109003, "CUSTOMER_MOBILE_REPEAT", "客户的电话号码重复"),
    CUSTOMER_INEXISTENCE(109004, "CUSTOMER_INEXISTENCE", "客户信息不存在"),


    CUSTOMER_ID_IS_NULL(109101, "CUSTOMER_ID_IS_NULL", "关联的客户ID不能为空"),
    CUSTOMER_ADDRESS_CITY_CODE_IS_NULL(109102, "CUSTOMER_ADDRESS_CITY_CODE_IS_NULL", "联系人地址所在城市不能为空"),
    CUSTOMER_ADDRESS_IS_NULL(109103, "CUSTOMER_ADDRESS_IS_NULL", "联系人地址详情不能为空"),
    CUSTOMER_ADDRESS_NAME_IS_NULL(109004, "CUSTOMER_ADDRESS_NAME_IS_NULL", "联系人的姓名不能为空"),
    CUSTOMER_GENDER_ERROR(109105, "CUSTOMER_GENDER_ERROR", "联系人性别错误"),
    CUSTOMER_ADDRESS_ISDEFAULT_ERROR(109106, "CUSTOMER_ADDRESS_ISDEFAULT_ERROR", "地址是否是默认参数错误"),
    CUSTOMER_ADDRESS_INEXISTENCE(109107, "CUSTOMER_ADDRESS_INEXISTENCE", "地址不存在"),
    CUSTOMER_HAS_NOT_DEFAULT_ADDRESS(109108, "CUSTOMER_HAS_NOT_DEFAULT_ADDRESS", "该客户没有默认地址"),
    CITY_CODE_INEXIST(109109, "CITY_CODE_INEXIST", "城市编码不存在"),
    CITY_IS_NOT_OPEN(109110, "CITY_IS_NOT_OPEN", "该城市尚未开通"),
    ;

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