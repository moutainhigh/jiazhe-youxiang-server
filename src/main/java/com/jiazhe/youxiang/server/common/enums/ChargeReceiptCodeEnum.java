package com.jiazhe.youxiang.server.common.enums;

/**
 * @author TU
 * @description
 * @date 2019-03-19.
 */
public enum ChargeReceiptCodeEnum {
    CHARGE_RECEIPT_IS_NOT_EXIST(119001, "CHARGE_RECEIPT_IS_NOT_EXIST", "消费凭证不存在"),
    EXCHANGE_POINT_IS_NULL(119002, "EXCHANGE_POINT_IS_NULL", "小票积分不能为空"),
    CUSTOMER_NAME_IS_NULL(119003, "CUSTOMER_NAME_IS_NULL", "客户姓名不能为空"),
    POS_CODE_IS_NULL(119004, "POS_CODE_IS_NULL", "pos机编号不能为空"),
    CARD_NO_IS_NULL(119005, "CARD_NO_IS_NULL", "银行卡号不能为空"),
    TRADE_TIME_IS_NULL(119006, "TRADE_TIME_IS_NULL", "交易时间不能为空"),
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
