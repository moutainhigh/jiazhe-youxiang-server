package com.jiazhe.youxiang.server.common.exceptions;

import com.jiazhe.youxiang.server.common.enums.WeChatPayCodeEnum;

/**
 * @author TU
 * @description
 * @date 2018/12/12.
 */
public class WeChatPayException extends CommonException {
    public WeChatPayException(WeChatPayCodeEnum weChatPayCodeEnum) {
        super(weChatPayCodeEnum.getCode(), weChatPayCodeEnum.getType(), weChatPayCodeEnum.getMessage());
    }
}
