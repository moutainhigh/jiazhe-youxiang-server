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
    CUSTOMER_NAME_IS_NULL(111004, "CUSTOMER_NAME_IS_NULL", "客户姓名不能为空"),
    CUSTOMER_MOBILE_IS_NULL(111005, "CUSTOMER_MOBILE_IS_NULL", "客户电话不能为空"),
    EXCHANGE_MONEY_IS_NULL(111006, "EXCHANGE_MONEY_IS_NULL", "消费积分不能为空"),
    CUSTOMER_MOBILE_IS_ILLEGAL(111007, "CUSTOMER_MOBILE_IS_ILLEGAL", "客户电话非法"),
    EXCHANGE_MONEY_IS_ILLEGAL(111008, "EXCHANGE_MONEY_IS_ILLEGAL", "消费积分不合法"),
    RECORD_HASS_PASSED(111009, "RECORD_HASS_PASSED", "该条记录已经通过审核，不允许修改"),
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
