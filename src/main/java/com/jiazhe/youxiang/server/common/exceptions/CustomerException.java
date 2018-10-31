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
    public CustomerException(Integer code, String type, String message) {
        super(code, type, message);
    }

    public CustomerException(CommonCodeEnum codeEnum) {
        super(codeEnum);
    }

    public CustomerException(ResponseMsg responseMsg) {
        super(responseMsg);
    }

    public CustomerException(CommonCodeEnum codeEnum, Throwable cause) {
        super(codeEnum, cause);
    }

    public CustomerException(Integer code, String type, String message, Throwable cause) {
        super(code, type, message, cause);
    }

    public CustomerException(ResponseMsg responseMsg, Throwable cause) {
        super(responseMsg, cause);
    }

    public CustomerException(CustomerCodeEnum customerCodeEnum) {
        super(customerCodeEnum.getCode(), customerCodeEnum.getType(), customerCodeEnum.getMessage());
    }
}