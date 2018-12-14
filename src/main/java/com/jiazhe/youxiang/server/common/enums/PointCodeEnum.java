package com.jiazhe.youxiang.server.common.enums;

/**
 * @author TU
 * @description
 * @date 2018/12/13.
 */
public enum PointCodeEnum {
    BATCH_NAME_IS_NULL(106001, "BATCH_NAME_IS_NULL", "批次名称为空"),
    POINT_NAME_IS_NULL(106002, "POINT_NAME_IS_NULL", "积分卡名称为空"),
    BATCH_EXPIRY_TIME_IS_NULL(106003, "BATCH_EXPIRY_TIME_IS_NULL", "批次过期时间为空"),
    NOT_VIRTUAL_NEED_AMOUNT(106004, "NOT_VIRTUAL_NEED_AMOUNT", "数量不能为空"),
    NOT_VIRTUAL_NEED_FACE_VALUE(106005, "NOT_VIRTUAL_NEED_FACE_VALUE", "面额不能为空"),
    POINT_EXPIRY_TIME_IS_NULL(106006, "POINT_EXPIRY_TIME_IS_NULL", "积分卡过期时间为空"),
    VIRTUAL_BATCH_CANNOT_GENERATE(106007, "VIRTUAL_BATCH_CANNOT_GENERATE", "虚拟批次不能生成兑换码"),
    CODE_GENERATED(106008, "CODE_GENERATED", "兑换码已经生成，不能再次生成"),
    BATCH_NOT_EXISTED(106009, "BATCH_NOT_EXISTED", "批次不存在"),
    EXCHANGE_CODE_NOT_EXISTED(106010, "EXCHANGE_CODE_NOT_EXISTED", "兑换码不存在"),
    EXCHANGE_CODE_EXPIRY_TIME_IS_NULL(106011, "EXCHANGE_CODE_EXPIRY_TIME_IS_NULL", "兑换码过期时间为空"),
    NO_CODE_TO_EXPORT(106012, "NO_CODE_TO_EXPORT", "没有兑换码可以导出"),
    CODE_2_RECORD_EXCEPTION(106013, "CODE_2_RECORD_EXCEPTION", "该兑换码兑换记录异常"),
    CARD_2_RECORD_EXCEPTION(106014, "CARD_2_RECORD_EXCEPTION", "该积分卡对应的兑换记录异常"),
    EXCHANGE_CODE_HAS_STOPED_USING(106015, "EXCHANGE_CODE_HAS_STOPED_USING", "该兑换码已停用"),
    EXCHANGE_CODE_HAS_USED(106016, "EXCHANGE_CODE_HAS_USED", "该兑换码已被使用"),
    EXCHANGE_CODE_HAS_EXPIRIED(106017, "EXCHANGE_CODE_HAS_EXPIRIED", "该兑换码已过期"),
    EXCHANGE_CODE_HAS_NOT_USED(106019, "EXCHANGE_CODE_HAS_NOT_USED", "该兑换码还未被使用"),
    EXCHANGE_CODE_HAS_NO_PAYMENT(106018, "EXCHANGE_CODE_HAS_NO_PAYMENT", "该兑换码还没有消费记录"),
    CARD_HAS_NO_PAYMENT(106019, "CARD_HAS_NO_PAYMENT", "该积分卡还没有消费记录"),
    CUSTOMER_NOT_EXIST(106020, "CUSTOMER_NOT_EXIST", "该客户不存在"),
    PROJECT_IS_NULL(106021, "PROJECT_IS_NULL", "项目不能为空"),
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
