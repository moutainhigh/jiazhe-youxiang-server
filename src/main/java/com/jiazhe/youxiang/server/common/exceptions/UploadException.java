/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.common.exceptions;

import com.jiazhe.youxiang.server.common.enums.UploadCodeEnum;

/**
 * 上传异常类
 *
 * @author niexiao
 * @created 2018/10/30
 */
public class UploadException extends CommonException {

    public UploadException(UploadCodeEnum uploadCodeEnum) {
        super(uploadCodeEnum.getCode(), uploadCodeEnum.getType(), uploadCodeEnum.getMessage());
    }

    public UploadException(UploadCodeEnum uploadCodeEnum, String message) {
        super(uploadCodeEnum.getCode(), uploadCodeEnum.getType(), message);
    }
}