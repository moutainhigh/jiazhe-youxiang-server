/*
 * Copyright (c) 2017 ue-link.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.common.enums;

import com.jiazhe.youxiang.server.common.constant.CommonConstant;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/10/15
 */
public enum DemoCodeEnum {
    INSERT_GOODS_ACTIVITY_ERROR(139011, CommonConstant.BUSINESS_ERROR, "插入卖品活动失败"),

    UPDATE_GOODS_ACTIVITY_ERROR(139012, CommonConstant.BUSINESS_ERROR, "更新卖品活动失败");

    private final Integer code;
    private final String type;
    private final String message;

    DemoCodeEnum(Integer code, String type, String message) {
        this.code = code;
        this.type = type;
        this.message = message;
    }

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