package com.jiazhe.youxiang.server.common.enums;

/**
 * @author TU
 * @description
 * @date 2018/10/30.
 */
public enum RechargeCardCodeEnum {

    BATCH_NAME_IS_NULL(106001, "BATCH_NAME_IS_NULL", "批次名称为空"),
    RECHARGE_CARD_NAME_IS_NULL(106002, "RECHARGE_CARD_NAME_IS_NULL", "充值卡名称为空"),
    BATCH_EXPIRT_TIME_IS_NULL(106003, "BATCH_EXPIRT_TIME_IS_NULL", "批次过期时间为空"),
    NOT_VIRTUAL_NEED_AMOUNT(106004, "NOT_VIRTUAL_NEED_AMOUNT", "数量不能为空"),
    NOT_VIRTUAL_NEED_FACE_VALUE(106005, "NOT_VIRTUAL_NEED_FACE_VALUE", "面额不能为空"),
    RECHARGE_CARD_EXPIRT_TIME_IS_NULL(106006, "RECHARGE_CARD_EXPIRT_TIME_IS_NULL", "充值卡过期时间为空"),
    VIRTUAL_BATCH_CANNOT_GENERATE(106007, "VIRTUAL_BATCH_CANNOT_GENERATE", "虚拟批次不能生成兑换码"),
    CODE_GENERATED(106008, "CODE_GENERATED", "兑换码已经生成，不能再次生成"),
    BATCH_NOT_EXISTED(106009, "BATCH_NOT_EXISTED", "批次不存在"),
    ;

    RechargeCardCodeEnum(Integer code, String type, String message) {
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
