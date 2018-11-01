/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.common.exceptions;

import com.jiazhe.youxiang.server.common.enums.CommonCodeEnum;
import com.jiazhe.youxiang.server.common.enums.CustomerCodeEnum;
import com.jiazhe.youxiang.server.vo.ResponseMsg;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/10/31
 */
public class CustomerException extends CommonException {
    public CustomerException(CustomerCodeEnum customerCodeEnum) {
        super(customerCodeEnum.getCode(), customerCodeEnum.getType(), customerCodeEnum.getMessage());
    }
}