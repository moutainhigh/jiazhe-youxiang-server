/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.common.exceptions;

import com.jiazhe.youxiang.server.common.enums.CommonCodeEnum;
import com.jiazhe.youxiang.server.common.enums.ProductCodeEnum;
import com.jiazhe.youxiang.server.vo.ResponseMsg;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/10/30
 */
public class ProductException extends CommonException {

    public ProductException(Integer code, String type, String message) {
        super(code, type, message);
    }

    public ProductException(CommonCodeEnum codeEnum) {
        super(codeEnum);
    }

    public ProductException(ResponseMsg responseMsg) {
        super(responseMsg);
    }

    public ProductException(CommonCodeEnum codeEnum, Throwable cause) {
        super(codeEnum, cause);
    }

    public ProductException(Integer code, String type, String message, Throwable cause) {
        super(code, type, message, cause);
    }

    public ProductException(ResponseMsg responseMsg, Throwable cause) {
        super(responseMsg, cause);
    }

    public ProductException(ProductCodeEnum productCodeEnum) {
        super(productCodeEnum.getCode(), productCodeEnum.getType(), productCodeEnum.getMessage());
    }
}