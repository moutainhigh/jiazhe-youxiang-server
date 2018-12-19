package com.jiazhe.youxiang.server.common.enums;

/**
 * @author tu
 * @description：
 * @date 2018/12/15
 */
public enum PartnerOrderCodeEnum {
    CUSTOMER_NAME_IS_NULL(117001, "CUSTOMER_NAME_IS_NULL", "客户姓名不能为空"),
    EXCHANGE_TIME_IS_NULL(117002, "EXCHANGE_TIME_IS_NULL", "兑换时间不能为空"),
    CUSTOMER_CITY_IS_NULL(117003, "CUSTOMER_CITY_IS_NULL", "城市不能为空"),
    PRODUCT_TYPE_IS_NULL(117004, "PRODUCT_TYPE_IS_NULL", "产品类型不能为空"),
    SERVICE_ITEM_IS_NULL(117005, "SERVICE_ITEM_IS_NULL", "服务项目不能为空"),
    PRE_PAY_IS_NULL(117006, "PRE_PAY_IS_NULL", "预付款不能为空"),
    APPEND_PAY_IS_NULL(117007, "SERVICE_ITEM_IS_NULL", "再次支付不能为空"),
    CUSTOMER_MOBILE_IS_VALID(117008, "CUSTOMER_MOBILE_IS_VALID", "电话号码非法"),
    ;

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
