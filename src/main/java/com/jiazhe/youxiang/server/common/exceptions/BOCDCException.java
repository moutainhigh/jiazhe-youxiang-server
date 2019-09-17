/*
 * Copyright (c) 2017 ue-link.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.common.exceptions;

import com.jiazhe.youxiang.server.common.enums.BOCDCCodeEnum;

/**
 * 中行储蓄卡异常
 *
 * @author niexiao
 * @created 2018/11/1
 */
public class BOCDCException extends CommonException {

    public BOCDCException(BOCDCCodeEnum bocdcCodeEnum) {
        super(bocdcCodeEnum.getCode(), bocdcCodeEnum.getType(), bocdcCodeEnum.getMessage());
    }

    public BOCDCException(Integer code, String type, String message) {
        super(code, type, message);
    }
}