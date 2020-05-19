/*
 * Copyright (c) 2017 ue-link.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.common.exceptions;

import com.jiazhe.youxiang.server.common.enums.DJBXCodeEnum;

/**
 * @author TU
 * @description 大家保险异常处理类
 * @date 2019/09/16.
 */
public class DJBXException extends CommonException {

    public DJBXException(DJBXCodeEnum djbxCodeEnum) {
        super(djbxCodeEnum.getCode(), djbxCodeEnum.getType(), djbxCodeEnum.getMessage());
    }

    public DJBXException(DJBXCodeEnum djbxCodeEnum, String message) {
        super(djbxCodeEnum.getCode(), djbxCodeEnum.getType(), djbxCodeEnum.getMessage() + ":" + message);
    }

    public DJBXException(Integer code, String type, String message) {
        super(code, type, message);
    }
}
