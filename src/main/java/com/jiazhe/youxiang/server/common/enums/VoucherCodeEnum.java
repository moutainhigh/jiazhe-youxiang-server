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
    CODE_2_RECORD_EXCEPTION(106012, "CODE_2_RECORD_EXCEPTION", "该兑换码兑换记录异常"),
    CARD_2_RECORD_EXCEPTION(107013, "CARD_2_RECORD_EXCEPTION", "该充值卡对应的兑换记录异常"),
    EXCHANGE_CODE_HAS_STOPED_USING(107014, "EXCHANGE_CODE_HAS_STOPED_USING", "该兑换码尚未启用"),
    EXCHANGE_CODE_HAS_USED(107015, "EXCHANGE_CODE_HAS_USED", "该兑换码已被使用"),
    EXCHANGE_CODE_HAS_EXPIRIED(107016, "EXCHANGE_CODE_HAS_EXPIRIED", "该兑换码已过期"),
    EXCHANGE_CODE_HAS_NOT_USED(107017, "EXCHANGE_CODE_HAS_NOT_USED", "该兑换码还未被使用"),
    EXCHANGE_CODE_HAS_NO_PAYMENT(107018, "EXCHANGE_CODE_HAS_NO_PAYMENT", "该兑换码没有消费记录"),
    VOUCHER_HAS_NO_PAYMENT(106019, "VOUCHER_HAS_NO_PAYMENT", "该代金券还没有消费记录"),
    CUSTOMER_NOT_EXIST(106020, "CUSTOMER_NOT_EXIST", "该客户不存在"),
    PROJECT_IS_NULL(116021, "PROJECT_IS_NULL", "项目不能为空"),
    CITY_IS_NULL(116022, "CITY_IS_NULL", "适用城市不能为空"),
    PRODUCT_IS_NULL(116023, "PRODUCT_IS_NULL", "适用商品不能为空"),
    VOUCHER_EFFECTIVE_TIME_IS_NULL(116024, "VOUCHER_EFFECTIVE_TIME_IS_NULL", "代金券生效时间为空"),
    VOUCHER_EFFECTIVE_TIME_LATER_BATCH_EXPIRY_TIME(116025, "VOUCHER_EFFECTIVE_TIME_LATER_BATCH_EXPIRY_TIME", "代金券生效时间不能晚于批次过期时间"),
    VOUCHER_EFFECTIVE_TIME_LATER_CODE_EXPIRY_TIME(116026, "VOUCHER_EFFECTIVE_TIME_LATER_CODE_EXPIRY_TIME", "代金券生效时间不能晚于兑换码过期时间"),
    VOUCHER_EFFECTIVE_TIME_LATER_VOUCHER_EXPIRY_TIME(116027, "VOUCHER_EFFECTIVE_TIME_LATER_VOUCHER_EXPIRY_TIME", "代金券生效时间不能晚于代金券过期时间"),
    BATCH_HAS_STOPPED_USING(116028, "BATCH_HAS_STOPPED_USING", "批次已经停用"),
    VOUCHER_NOT_EXIST(116029, "VOUCHER_NOT_EXIST", "代金券不存在"),;

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