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
 * @created 2018/10/29
 */
public enum ProjectCodeEnum {

    PROJECT_NAME_IS_NULL(105001, "PROJECT_NAME_IS_NULL", "项目名称为空"),
    PROJECT_POINT_CONVERSION_RATE_ERROR(105002, "PROJECT_POINT_CONVERSION_RATE_ERROR", "积分兑换比例有误"),
    ;

    ProjectCodeEnum(Integer code, String type, String message) {
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