package com.jiazhe.youxiang.server.common.exceptions;

import com.jiazhe.youxiang.server.common.enums.CommonCodeEnum;
import com.jiazhe.youxiang.server.common.enums.RechargeCardCodeEnum;
import com.jiazhe.youxiang.server.vo.ResponseMsg;

/**
 * @author TU
 * @description
 * @date 2018/11/1.
 */
public class RechargeCardException extends CommonException {
    public RechargeCardException(Integer code, String type, String message) {
        super(code, type, message);
    }

    public RechargeCardException(CommonCodeEnum codeEnum) {
        super(codeEnum);
    }

    public RechargeCardException(ResponseMsg responseMsg) {
        super(responseMsg);
    }

    public RechargeCardException(CommonCodeEnum codeEnum, Throwable cause) {
        super(codeEnum, cause);
    }

    public RechargeCardException(Integer code, String type, String message, Throwable cause) {
        super(code, type, message, cause);
    }

    public RechargeCardException(ResponseMsg responseMsg, Throwable cause) {
        super(responseMsg, cause);
    }

    public RechargeCardException(RechargeCardCodeEnum rechargeCardCodeEnum) {
        super(rechargeCardCodeEnum.getCode(), rechargeCardCodeEnum.getType(), rechargeCardCodeEnum.getMessage());
    }
}
