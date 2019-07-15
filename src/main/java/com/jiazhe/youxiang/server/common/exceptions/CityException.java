/*
 * Copyright (c) 2017 ue-link.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.common.exceptions;

import com.jiazhe.youxiang.server.common.enums.CityCodeEnum;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/11/1
 */
public class CityException extends CommonException {

    public CityException(CityCodeEnum cityCodeEnum) {
        super(cityCodeEnum.getCode(), cityCodeEnum.getType(), cityCodeEnum.getMessage());
    }
}