package com.jiazhe.youxiang.server.common.exceptions;

import com.jiazhe.youxiang.server.common.enums.RechargeCardCodeEnum;

/**
 * @author TU
 * @description
 * @date 2018/11/1.
 */
public class RechargeCardException extends CommonException {

    public RechargeCardException(RechargeCardCodeEnum rechargeCardCodeEnum) {
        super(rechargeCardCodeEnum.getCode(), rechargeCardCodeEnum.getType(), rechargeCardCodeEnum.getMessage());
    }
}
