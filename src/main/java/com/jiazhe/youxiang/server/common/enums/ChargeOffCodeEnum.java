package com.jiazhe.youxiang.server.common.enums;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2019-09-11
 */
public enum ChargeOffCodeEnum {

    CITY_CODE_IS_NULL(124001, "CITY_CODE_IS_NULL", "兑换城市不能为空"),
    BANK_OUTLETS_NAME_IS_NULL(124002, "BANK_OUTLETS_NAME_IS_NULL", "银行信息不能为空"),
    CHARGE_OFF_TYPE_ERROR(124003, "CHARGE_OFF_TYPE_ERROR", "核销兑换类型错误"),
    KEYT_LIST_IS_NULL(124004, "KEYT_LIST_IS_NULL", "核销密码不能为空"),
    TOTAL_POINT_IS_NULL(124005, "TOTAL_POINT_IS_NULL", "核销总积分不能为空"),
    CUSTOMER_NAME_IS_NULL(124006, "CUSTOMER_NAME_IS_NULL", "客户姓名不能为空"),
    CUSTOMER_MOBILE_IS_NULL(124007, "CUSTOMER_MOBILE_IS_NULL", "客户手机号码不能为空"),
    CUSTOMER_MOBILE_ERROR(124008, "CUSTOMER_MOBILE_ERROR", "客户手机号码格式错误"),
    CHARGE_OFF_STATUS_ERROR(124009, "CHARGE_OFF_STATUS_ERROR", "核销兑换状态错误"),
    PRODUCT_VALUE_IS_NULL(124010, "PRODUCT_VALUE_IS_NULL", "兑换商品价值不能为空"),
    CHARGE_OFF_ID_IS_NULL(124011, "CHARGE_OFF_ID_IS_NULL", "核销Id不能为空"),
    KEYT_IS_NULL(124012, "KEYT_IS_NULL", "兑换码密码不能为空"),
    KEYT_ERROR(124013, "KEYT_ERROR", "兑换码密码相关错误"),
    CHARGE_OFF_NOT_EXIST(124014, "CHARGE_OFF_NOT_EXIST", "核销记录不存在"),
    ;

    ChargeOffCodeEnum(Integer code, String type, String message) {
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
