package com.jiazhe.youxiang.server.common.exceptions;

import com.jiazhe.youxiang.server.common.enums.ChargeOffCodeEnum;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2020/03/04
 */
public class ChargeOffException extends CommonException {

    public ChargeOffException(ChargeOffCodeEnum chargeOffCodeEnum) {
        super(chargeOffCodeEnum.getCode(), chargeOffCodeEnum.getType(), chargeOffCodeEnum.getMessage());
    }

    public ChargeOffException(ChargeOffCodeEnum chargeOffCodeEnum, String message) {
        super(chargeOffCodeEnum.getCode(), chargeOffCodeEnum.getType(), message);
    }

    public ChargeOffException(Integer code, String type, String message) {
        super(code, type, message);
    }
}
