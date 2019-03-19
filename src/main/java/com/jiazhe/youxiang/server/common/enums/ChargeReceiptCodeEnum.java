package com.jiazhe.youxiang.server.common.enums;

/**
 * @author TU
 * @description
 * @date 2019-03-19.
 */
public enum ChargeReceiptCodeEnum {
    CHARGE_RECEIPT_IS_NOT_EXIST(119001, "CHARGE_RECEIPT_IS_NOT_EXIST", "消费凭证不存在"),
    ;

    ChargeReceiptCodeEnum(Integer code, String type, String message) {
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
