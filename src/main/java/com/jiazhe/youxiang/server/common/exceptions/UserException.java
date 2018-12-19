package com.jiazhe.youxiang.server.common.exceptions;

import com.jiazhe.youxiang.server.common.enums.UserCodeEnum;

/**
 * @author TU
 * @description
 * @date 2018/11/1.
 */
public class UserException extends CommonException {

    public UserException(UserCodeEnum userCodeEnum) {
        super(userCodeEnum.getCode(), userCodeEnum.getType(), userCodeEnum.getMessage());
    }
}
