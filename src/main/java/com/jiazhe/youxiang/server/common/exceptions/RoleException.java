package com.jiazhe.youxiang.server.common.exceptions;

import com.jiazhe.youxiang.server.common.enums.CommonCodeEnum;
import com.jiazhe.youxiang.server.common.enums.RoleCodeEnum;
import com.jiazhe.youxiang.server.vo.ResponseMsg;

/**
 * @author TU
 * @description
 * @date 2018/11/1.
 */
public class RoleException extends CommonException {
    public RoleException(Integer code, String type, String message) {
        super(code, type, message);
    }

    public RoleException(CommonCodeEnum codeEnum) {
        super(codeEnum);
    }

    public RoleException(ResponseMsg responseMsg) {
        super(responseMsg);
    }

    public RoleException(CommonCodeEnum codeEnum, Throwable cause) {
        super(codeEnum, cause);
    }

    public RoleException(Integer code, String type, String message, Throwable cause) {
        super(code, type, message, cause);
    }

    public RoleException(ResponseMsg responseMsg, Throwable cause) {
        super(responseMsg, cause);
    }

    public RoleException(RoleCodeEnum roleCodeEnum) {
        super(roleCodeEnum.getCode(), roleCodeEnum.getType(), roleCodeEnum.getMessage());
    }
}
