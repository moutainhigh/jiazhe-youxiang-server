package com.jiazhe.youxiang.server.common.enums;

/**
 * @author tu
 * @description：
 * @date 2019-05-13
 */
public enum MaterialCodeEnum {

    PAYEE_NOT_EXIST(121001,"PAYEE_NOT_EXIST","收款人不存在"),
    TRANSFER_AMOUNT_ILLEGAL(121002,"TRANSFER_AMOUNT_ILLEGAL","转账金额非法"),
    MATERIAL_VALUE_ILLEGAL(121003,"MATERIAL_VALUE_ILLEGAL","采购实物价值非法"),
    TRANSFER_TIME_IS_NULL(121004,"TRANSFER_TIME_IS_NULL","转账时间为空"),
    MATERIAL_INFO_IS_NOT_EXIST(121005,"MATERIAL_INFO_IS_NOT_EXIST","转账记录不存在"),

    ;

    MaterialCodeEnum(Integer code, String type, String message) {
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
