package com.jiazhe.youxiang.server.common.enums;

/**
 * @author tu
 * @description：
 * @date 2018/12/15
 */
public enum PartnerOrderCodeEnum {
    CUSTOMER_NAME_IS_NULL(117001, "CUSTOMER_NAME_IS_NULL", "客户姓名不能为空"),
    CUSTOMER_MOBILE_IS_NULL(117002, "CUSTOMER_MOBILE_IS_NULL", "客户电话不能为空"),
    CUSTOMER_MOBILE_IS_VALID(117003, "CUSTOMER_MOBILE_IS_VALID", "客户电话号码非法"),
    CUSTOMER_CITY_IS_NULL(117004, "CUSTOMER_CITY_IS_NULL", "城市不能为空"),
    KEYT_IS_NULL(117005, "KEYT_IS_NULL", "兑换秘钥不能为空"),
    EXCHANGE_TIME_IS_NULL(117006, "EXCHANGE_TIME_IS_NULL", "兑换时间不能为空"),
    SERVICE_TIME_IS_NULL(117007, "SERVICE_TIME_IS_NULL", "预约时间不能为空"),
    CUSTOMER_ADDRESS_IS_NULL(117008, "CUSTOMER_ADDRESS_IS_NULL", "预约地点不能为空"),
    ORDER_SOURCE_IS_NULL(117009, "ORDER_SOURCE_IS_NULL", "订单来源不能为空"),
    WORKER_NAME_IS_NULL(117010, "WORKER_NAME_IS_NULL", "保洁师姓名不能为空"),
    WORKER_MOBILE_IS_NULL(117011, "WORKER_MOBILE_IS_NULL", "保洁师电话不能为空"),
    WORKER_MOBILE_IS_VALID(117012, "WORKER_MOBILE_IS_VALID", "保洁师电话号码非法"),
    PARTNER_IS_NULL(117013, "PARTNER_IS_NULL", "服务商家不能为空"),
    SERVICE_ITEM_IS_NULL(117014, "SERVICE_ITEM_IS_NULL", "服务项目不能为空"),
    PRE_PAY_IS_NULL(117015, "PRE_PAY_IS_NULL", "预付款不能为空"),
    APPEND_PAY_IS_NULL(117016, "APPEND_PAY_IS_NULL", "再次支付不能为空"),;

    PartnerOrderCodeEnum(Integer code, String type, String message) {
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
