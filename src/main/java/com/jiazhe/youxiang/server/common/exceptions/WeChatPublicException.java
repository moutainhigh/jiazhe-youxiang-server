/*
 * Copyright (c) 2017 ue-link.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.common.exceptions;

import com.jiazhe.youxiang.server.common.enums.WeChatPublicCodeEnum;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2019/1/8
 */
public class WeChatPublicException extends CommonException {
    public WeChatPublicException(WeChatPublicCodeEnum weChatPublicCodeEnum) {
        super(weChatPublicCodeEnum.getCode(), weChatPublicCodeEnum.getType(), weChatPublicCodeEnum.getMessage());
    }
}