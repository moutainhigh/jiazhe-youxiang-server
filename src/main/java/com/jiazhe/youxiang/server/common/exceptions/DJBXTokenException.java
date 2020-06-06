/*
 * Copyright (c) 2019 橙谊科技
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.common.exceptions;

import com.jiazhe.youxiang.server.common.enums.DJBXCodeEnum;

/**
 * @author tu
 * @version 1.0
 * @description TODO
 * @created 2020-06-06 10:33
 */
public class DJBXTokenException  extends CommonException{

    public DJBXTokenException(DJBXCodeEnum djbxCodeEnum) {
        super(djbxCodeEnum.getCode(), djbxCodeEnum.getType(), djbxCodeEnum.getMessage());
    }

    public DJBXTokenException(DJBXCodeEnum djbxCodeEnum, String message) {
        super(djbxCodeEnum.getCode(), djbxCodeEnum.getType(), djbxCodeEnum.getMessage() + ":" + message);
    }

    public DJBXTokenException(Integer code, String type, String message) {
        super(code, type, message);
    }
}
