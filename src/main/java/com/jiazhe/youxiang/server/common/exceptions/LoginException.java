package com.jiazhe.youxiang.server.common.exceptions;

import com.jiazhe.youxiang.server.common.enums.LoginCodeEnum;

/**
 * @author TU
 * @description
 * @date 2018/11/1.
 */
public class LoginException extends CommonException {

    public LoginException(LoginCodeEnum loginCodeEnum) {
        super(loginCodeEnum.getCode(), loginCodeEnum.getType(), loginCodeEnum.getMessage());
    }
}
