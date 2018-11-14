package com.jiazhe.youxiang.server.common.exceptions;

import com.jiazhe.youxiang.server.common.enums.CommonCodeEnum;
import com.jiazhe.youxiang.server.common.enums.EleProductCodeEnum;
import com.jiazhe.youxiang.server.common.enums.LoginCodeEnum;
import com.jiazhe.youxiang.server.vo.ResponseMsg;

/**
 * @author TU
 * @description
 * @date 2018/11/12.
 */
public class EleProductCodeException extends CommonException{
    public EleProductCodeException(Integer code, String type, String message) {
        super(code, type, message);
    }

    public EleProductCodeException(CommonCodeEnum codeEnum) {
        super(codeEnum);
    }

    public EleProductCodeException(ResponseMsg responseMsg) {
        super(responseMsg);
    }

    public EleProductCodeException(CommonCodeEnum codeEnum, Throwable cause) {
        super(codeEnum, cause);
    }

    public EleProductCodeException(Integer code, String type, String message, Throwable cause) {
        super(code, type, message, cause);
    }

    public EleProductCodeException(ResponseMsg responseMsg, Throwable cause) {
        super(responseMsg, cause);
    }

    public EleProductCodeException(EleProductCodeEnum eleProductCodeEnum) {
        super(eleProductCodeEnum.getCode(), eleProductCodeEnum.getType(), eleProductCodeEnum.getMessage());
    }
}
