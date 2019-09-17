/*
 * Copyright (c) 2017 ue-link.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.common.exceptions;

import com.jiazhe.youxiang.server.common.enums.CommonCodeEnum;
import com.jiazhe.youxiang.server.common.enums.DemoCodeEnum;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/10/15
 */
public class DemoException extends CommonException {
    public DemoException(CommonCodeEnum codeEnum) {
        super(codeEnum);
    }

    public DemoException(DemoCodeEnum codeEnum) {
        super(codeEnum.getCode(), codeEnum.getType(), codeEnum.getMessage());
    }

}