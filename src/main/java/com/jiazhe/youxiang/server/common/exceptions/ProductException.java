/*
 * Copyright (c) 2017 ue-link.com
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

    public ProductException(ProductCodeEnum productCodeEnum) {
        super(productCodeEnum.getCode(), productCodeEnum.getType(), productCodeEnum.getMessage());
    }
}