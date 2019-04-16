package com.jiazhe.youxiang.server.common.exceptions;

import com.jiazhe.youxiang.server.common.enums.MessageCodeEnum;

/**
 * @author TU
 * @description
 * @date 2019-04-08.
 */
public class MessageException extends CommonException {

    public MessageException(MessageCodeEnum messageCodeEnum) {
        super(messageCodeEnum.getCode(), messageCodeEnum.getType(), messageCodeEnum.getMessage());
    }

    public MessageException(Integer code, String type, String message) {
        super(code, type, message);
    }
}
