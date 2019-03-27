package com.jiazhe.youxiang.server.common.exceptions;

import com.jiazhe.youxiang.server.common.enums.ChargeReceiptCodeEnum;

/**
 * @author TU
 * @description
 * @date 2019-03-19.
 */
public class ChargeReceiptException extends CommonException {

    public ChargeReceiptException(ChargeReceiptCodeEnum auditRecordCodeEnum) {
        super(auditRecordCodeEnum.getCode(), auditRecordCodeEnum.getType(), auditRecordCodeEnum.getMessage());
    }
}
