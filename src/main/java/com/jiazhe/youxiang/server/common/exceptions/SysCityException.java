/*
 * Copyright (c) 2017 ue-link.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.common.exceptions;

import com.jiazhe.youxiang.server.common.enums.CustomerCodeEnum;
import com.jiazhe.youxiang.server.common.enums.SysCityCodeEnum;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/11/6
 */
public class SysCityException  extends CommonException {
    public SysCityException(SysCityCodeEnum sysCityCodeEnum) {
        super(sysCityCodeEnum.getCode(), sysCityCodeEnum.getType(), sysCityCodeEnum.getMessage());
    }
}