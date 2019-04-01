/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.common.enums;

/**
 * 上传的错误码
 *
 * @author niexiao
 * @created 2018/11/27
 */
public enum UploadCodeEnum {
    IMG_IS_NOT_NULL(114001, "IMG_IS_NOT_NULL", "上传图片不能为空"),
    IMG_TYPE_ERROR(114002, "IMG_TYPE_ERROR", "上传图片类型不符"),

    COS_SERVICE_ERROR(114901, "COS_SERVICE_ERROR", "COS服务端异常"),
    COS_CLIENT_ERROR(114902, "COS_CLIENT_ERROR", "COS客户端端异常"),
    OTHER_ERROR(114999, "OTHER_ERROR", "上传过程中发生未知异常"),;

    UploadCodeEnum(Integer code, String type, String message) {
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