package com.jiazhe.youxiang.server.common.enums;

/**
 * @author TU
 * @description
 * @date 2018/11/20.
 */
public enum AuditRecordCodeEnum {

    AUDIT_REASON_IS_NULL(111001, "AUDIT_REASON_IS_NULL", "审核理由不能为空"),
    VERSION_IS_CHANGED(111002, "VERSION_IS_CHANGED", "该条记录已经被修改，请刷新"),
    CUSTOMER_NOT_EXIST(111003, "CUSTOMER_NOT_EXIST", "该客户不存在，请先添加"),
    ;

    AuditRecordCodeEnum(Integer code, String type, String message) {
        this.code = code;
        this.type = type;
        this.message = message;
    }

    private final Integer code;
    private final String type;
    private final String message;

    public Integer getCode() {
        return code;
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }
}
