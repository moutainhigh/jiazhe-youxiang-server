package com.jiazhe.youxiang.server.common.exceptions;

import com.jiazhe.youxiang.server.common.enums.AuditRecordCodeEnum;

/**
 * @author TU
 * @description
 * @date 2018/11/20.
 */
public class AuditRecordException extends CommonException {

    public AuditRecordException(AuditRecordCodeEnum auditRecordCodeEnum) {
        super(auditRecordCodeEnum.getCode(), auditRecordCodeEnum.getType(), auditRecordCodeEnum.getMessage());
    }
}
