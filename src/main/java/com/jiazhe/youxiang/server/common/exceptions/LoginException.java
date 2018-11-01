package com.jiazhe.youxiang.server.common.exceptions;

import com.jiazhe.youxiang.server.common.enums.CommonCodeEnum;
import com.jiazhe.youxiang.server.common.enums.LoginCodeEnum;
import com.jiazhe.youxiang.server.vo.ResponseMsg;

/**
 * @author TU
 * @description
 * @date 2018/11/1.
 */
public class LoginException extends CommonException {
    public LoginException(Integer code, String type, String message) {
        super(code, type, message);
    }

    public LoginException(CommonCodeEnum codeEnum) {
        super(codeEnum);
    }

    public LoginException(ResponseMsg responseMsg) {
        super(responseMsg);
    }

    public LoginException(CommonCodeEnum codeEnum, Throwable cause) {
        super(codeEnum, cause);
    }

    public LoginException(Integer code, String type, String message, Throwable cause) {
        super(code, type, message, cause);
    }

    public LoginException(ResponseMsg responseMsg, Throwable cause) {
        super(responseMsg, cause);
    }

    public LoginException(LoginCodeEnum loginCodeEnum) {
        super(loginCodeEnum.getCode(), loginCodeEnum.getType(), loginCodeEnum.getMessage());
    }
}
