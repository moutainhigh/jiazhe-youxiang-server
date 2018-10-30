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
 * @created 2018/10/30
 */
public enum ProductCodeEnum {

    PRODUCT_CATEGORY_NAME_IS_NULL(110001, "PRODUCT_CATEGORY_NAME_IS_NULL", "商品分类名称为空"),
    PUTAWAY_STATUS_ERROR(110002, "PUTAWAY_STATUS_ERROR", "上架状态错误");

    ProductCodeEnum(Integer code, String type, String message) {
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
