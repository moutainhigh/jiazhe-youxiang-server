/*
 * Copyright (c) 2017 ue-link.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.common.exceptions;

import com.jiazhe.youxiang.server.common.enums.BOCCCCodeEnum;
import com.jiazhe.youxiang.server.common.enums.BOCDCCodeEnum;

/**
 * @author TU
 * @description 中行信用卡异常
 * @date 2019/09/16.
 */
public class BOCCCException extends CommonException {

    public BOCCCException(BOCCCCodeEnum bocccCodeEnum) {
        super(bocccCodeEnum.getCode(), bocccCodeEnum.getType(), bocccCodeEnum.getMessage());
    }

    public BOCCCException(Integer code, String type, String message) {
        super(code, type, message);
    }
}