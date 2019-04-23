package com.jiazhe.youxiang.server.common.enums;

/**
 * @author TU
 * @description
 * @date 2018/12/13.
 */
public enum PointCodeEnum {
    BATCH_NAME_IS_NULL(116001, "BATCH_NAME_IS_NULL", "批次名称不能为空"),
    POINT_NAME_IS_NULL(116002, "POINT_NAME_IS_NULL", "积分卡名称不能为空"),
    BATCH_EXPIRY_TIME_IS_NULL(116003, "BATCH_EXPIRY_TIME_IS_NULL", "批次过期时间不能为空"),
    NOT_VIRTUAL_NEED_AMOUNT(116004, "NOT_VIRTUAL_NEED_AMOUNT", "数量不能为空"),
    NOT_VIRTUAL_NEED_FACE_VALUE(116005, "NOT_VIRTUAL_NEED_FACE_VALUE", "面额不能为空"),
    POINT_EXPIRY_TIME_IS_NULL(116006, "POINT_EXPIRY_TIME_IS_NULL", "积分卡过期时间为空"),
    VIRTUAL_BATCH_CANNOT_GENERATE(116007, "VIRTUAL_BATCH_CANNOT_GENERATE", "虚拟批次不能生成兑换码"),
    CODE_GENERATED(116008, "CODE_GENERATED", "兑换码已经生成，不能再次生成"),
    BATCH_NOT_EXISTED(116009, "BATCH_NOT_EXISTED", "批次不存在"),
    EXCHANGE_CODE_NOT_EXISTED(116010, "EXCHANGE_CODE_NOT_EXISTED", "兑换码不存在"),
    EXCHANGE_CODE_EXPIRY_TIME_IS_NULL(116011, "EXCHANGE_CODE_EXPIRY_TIME_IS_NULL", "兑换码过期时间为空"),
    NO_CODE_TO_EXPORT(116012, "NO_CODE_TO_EXPORT", "没有兑换码可以导出"),
    CODE_2_RECORD_EXCEPTION(116013, "CODE_2_RECORD_EXCEPTION", "该兑换码兑换记录异常"),
    CARD_2_RECORD_EXCEPTION(116014, "CARD_2_RECORD_EXCEPTION", "该积分卡对应的兑换记录异常"),
    EXCHANGE_CODE_HAS_STOPED_USING(116015, "EXCHANGE_CODE_HAS_STOPED_USING", "该兑换码尚未启用"),
    EXCHANGE_CODE_HAS_USED(116016, "EXCHANGE_CODE_HAS_USED", "该兑换码已被使用"),
    EXCHANGE_CODE_HAS_EXPIRIED(116017, "EXCHANGE_CODE_HAS_EXPIRIED", "该兑换码已过期"),
    EXCHANGE_CODE_HAS_NOT_USED(116019, "EXCHANGE_CODE_HAS_NOT_USED", "该兑换码还未被使用"),
    EXCHANGE_CODE_HAS_NO_PAYMENT(116018, "EXCHANGE_CODE_HAS_NO_PAYMENT", "该兑换码还没有消费记录"),
    CARD_HAS_NO_PAYMENT(116019, "CARD_HAS_NO_PAYMENT", "该积分卡还没有消费记录"),
    CUSTOMER_NOT_EXIST(116020, "CUSTOMER_NOT_EXIST", "该客户不存在"),
    PROJECT_IS_NULL(116021, "PROJECT_IS_NULL", "项目不能为空"),
    CITY_IS_NULL(116022, "CITY_IS_NULL", "适用城市为空"),
    PRODUCT_IS_NULL(116023, "PRODUCT_IS_NULL", "适用商品为空"),
    POINT_EFFECTIVE_TIME_IS_NULL(116024,"POINT_EFFECTIVE_TIME_IS_NULL" , "积分卡生效时间为空"),
    QRCODE_DECRYPT_ERROR(116025, "QRCODE_DECRYPT_ERROR", "二维码解密失败"),
    QRCODE_HAS_CHARGED(116026, "QRCODE_HAS_CHARGED", "该二维码已经兑换过"),
    CUSTOMER_IS_NOT_LOGIN(116027, "CUSTOMER_IS_NOT_LOGIN", "客户没有登录"),
    QRCODE_CHARGE_TIME_NOT_YET(116028, "QRCODE_CHARGE_TIME_ERROR", "积分扣减24小时后方可兑换"),
    QRCODE_CHARGE_TIME_ERROR(116029, "QRCODE_CHARGE_TIME_ERROR", "交易时间解析异常"),
    QRCODE_HAS_NOT_BATCH(116030, "QRCODE_HAS_NOT_BATCH", "没有找到可兑换的批次"),
    QRCODE_BATCH_ERROR(116031, "QRCODE_BATCH_ERROR", "二维码兑换批次记录异常"),
    MERCHANT_NO_REPEAT(116032,"MERCHANT_NO_REPEAT" ,"该商户号批次已经存在" ),
    POINT_EFFECTIVE_TIME_LATER_BATCH_EXPIRY_TIME(116033, "POINT_EFFECTIVE_TIME_LATER_BATCH_EXPIRY_TIME", "积分卡生效时间不能晚于批次过期时间"),
    POINT_EFFECTIVE_TIME_LATER_CODE_EXPIRY_TIME(116034, "POINT_EFFECTIVE_TIME_LATER_CODE_EXPIRY_TIME", "积分卡生效时间不能晚于兑换码过期时间"),
    POINT_EFFECTIVE_TIME_LATER_POINT_EXPIRY_TIME(116035, "POINT_EFFECTIVE_TIME_LATER_POINT_EXPIRY_TIME", "积分卡生效时间不能晚于积分卡过期时间"),
    BATCH_HAS_STOPPED_USING(116036, "BATCH_HAS_STOPPED_USING", "批次已经停用"),
    PROJECT_IS_NOT_EXIST(116037, "PROJECT_IS_NOT_EXIST", "所属项目不存在"),
    POINT_NOT_EXIST(116038, "POINT_NOT_EXIST", "该积分卡不存在"),
    CODE_HAS_START_USING(116039, "CODE_HAS_START_USING", "兑换码已经被启用"),
    ;

    PointCodeEnum(Integer code, String type, String message) {
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
