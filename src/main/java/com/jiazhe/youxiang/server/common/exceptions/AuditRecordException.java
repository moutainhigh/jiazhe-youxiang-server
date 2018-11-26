package com.jiazhe.youxiang.server.common.exceptions;

import com.jiazhe.youxiang.server.common.enums.AuditRecordCodeEnum;
import com.jiazhe.youxiang.server.common.enums.CommonCodeEnum;
import com.jiazhe.youxiang.server.vo.ResponseMsg;

/**
 * @author TU
 * @description
 * @date 2018/11/20.
 */
public class AuditRecordException extends CommonException {
    public AuditRecordException(Integer code, String type, String message) {
        super(code, type, message);
    }

    public AuditRecordException(CommonCodeEnum codeEnum) {
        super(codeEnum);
    }

    public AuditRecordException(ResponseMsg responseMsg) {
        super(responseMsg);
    }

    public AuditRecordException(CommonCodeEnum codeEnum, Throwable cause) {
        super(codeEnum, cause);
    }

    public AuditRecordException(Integer code, String type, String message, Throwable cause) {
        super(code, type, message, cause);
    }

    public AuditRecordException(ResponseMsg responseMsg, Throwable cause) {
        super(responseMsg, cause);
    }

    public AuditRecordException(AuditRecordCodeEnum auditRecordCodeEnum) {
        super(auditRecordCodeEnum.getCode(), auditRecordCodeEnum.getType(), auditRecordCodeEnum.getMessage());
    }
}
