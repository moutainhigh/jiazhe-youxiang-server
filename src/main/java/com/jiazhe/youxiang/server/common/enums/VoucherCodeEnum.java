package com.jiazhe.youxiang.server.common.enums;

/**
 * @author tu
 * @description：
 * @date 2018/11/3
 */
public enum VoucherCodeEnum { 
    BATCH_NAME_IS_NULL(107001, "BATCH_NAME_IS_NULL", "批次名称为空"),
    VOUCHER_NAME_IS_NULL(107002, "VOUCHER_NAME_IS_NULL", "代金券名称为空"),
    BATCH_EXPIRY_TIME_IS_NULL(107003, "BATCH_EXPIRY_TIME_IS_NULL", "批次过期时间为空"),
    AMOUNT_IS_NULL(107004, "AMOUNT_IS_NULL", "数量不能为空"),
    COUNT_IS_NULL(107005, "COUNT_IS_NULL", "兑换数量不能为空"),
    VOUCHER_EXPIRY_TIME_IS_NULL(107006, "VOUCHER_EXPIRY_TIME_IS_NULL", "代金券过期时间为空"),
    CODE_GENERATED(107007, "CODE_GENERATED", "兑换码已经生成，不能再次生成"),
    BATCH_NOT_EXISTED(107008, "BATCH_NOT_EXISTED", "批次不存在"),
    EXCHANGE_CODE_NOT_EXISTED(107009, "EXCHANGE_CODE_NOT_EXISTED", "兑换码不存在"),
    EXCHANGE_CODE_EXPIRY_TIME_IS_NULL(107010, "EXCHANGE_CODE_EXPIRY_TIME_IS_NULL", "兑换码过期时间为空"),
    NO_CODE_TO_EXPORT(107011, "NO_CODE_TO_EXPORT", "没有兑换码可以导出"),
    CARD_2_RECORD_EXCEPTION(107012, "CARD_2_RECORD_EXCEPTION", "该充值卡对应的兑换记录异常"),
    EXCHANGE_CODE_HAS_STOPED_USING(107013, "EXCHANGE_CODE_HAS_STOPED_USING", "该兑换码已停用"),
    EXCHANGE_CODE_HAS_USED(107014, "EXCHANGE_CODE_HAS_USED", "该兑换码已被使用"),
    EXCHANGE_CODE_HAS_EXPIRIED(107015, "EXCHANGE_CODE_HAS_EXPIRIED", "该兑换码已过期"),
    ;

    VoucherCodeEnum(Integer code, String type, String message) {
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