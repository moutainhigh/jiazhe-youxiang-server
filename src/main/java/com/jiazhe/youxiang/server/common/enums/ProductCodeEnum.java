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
    PUTAWAY_STATUS_ERROR(110001, "PUTAWAY_STATUS_ERROR", "上架状态错误"),
    PRODUCT_CATEGORY_NAME_IS_NULL(110101, "PRODUCT_CATEGORY_NAME_IS_NULL", "商品分类名称不能为空"),
    PRODUCT_NAME_IS_NULL(110201, "PRODUCT_NAME_IS_NULL", "商品名称为空"),
    PRODUCT_CATEGORY_ID_IS_NULL(110202, "PRODUCT_CATEGORY_ID_IS_NULL", "商品分类ID不能为空"),
    PRODUCT_TYPE_ERROR(110203, "PRODUCT_TYPE_ERROR", "商品类别错误"),
    PRODUCT_ID_IS_NULL(110301, "PRODUCT_ID_IS_NULL", "商品ID不能为空"),
    PRODUCT_CITY_CODE_IS_NULL(110302, "PRODUCT_CITY_CODE_IS_NULL", "商品可用城市集合不能为空"),
    PRODUCT_PRICE_ERROR(110303, "PRODUCT_PRICE_ERROR", "商品价格错误"),
    PRODUCT_PRICE_ID_IS_NULL(110304, "PRODUCT_PRICE_ID_IS_NULL", "商品价格ID不能为空"),
    PRODUCT_PRICE_CONFLICT_ERROR(110305, "PRODUCT_PRICE_CONFLICT_ERROR", "同一商品在同一城市的价格发生冲突"),
    PRODUCT_PRICE_BATCH_ADD_ERROR(110306, "PRODUCT_PRICE_BATCH_ADD_ERROR", "批量添加商品价格错误");





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
