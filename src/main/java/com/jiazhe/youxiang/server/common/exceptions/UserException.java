package com.jiazhe.youxiang.server.common.exceptions;

import com.jiazhe.youxiang.server.common.enums.CommonCodeEnum;
import com.jiazhe.youxiang.server.common.enums.UserCodeEnum;
import com.jiazhe.youxiang.server.vo.ResponseMsg;

/**
 * @author TU
 * @description
 * @date 2018/11/1.
 */
public class UserException extends CommonException {

    public UserException(Integer code, String type, String message) {
        super(code, type, message);
    }

    public UserException(CommonCodeEnum codeEnum) {
        super(codeEnum);
    }

    public UserException(ResponseMsg responseMsg) {
        super(responseMsg);
    }

    public UserException(CommonCodeEnum codeEnum, Throwable cause) {
        super(codeEnum, cause);
    }

    public UserException(Integer code, String type, String message, Throwable cause) {
        super(code, type, message, cause);
    }

    public UserException(ResponseMsg responseMsg, Throwable cause) {
        super(responseMsg, cause);
    }

    public UserException(UserCodeEnum userCodeEnum) {
        super(userCodeEnum.getCode(), userCodeEnum.getType(), userCodeEnum.getMessage());
    }
}
