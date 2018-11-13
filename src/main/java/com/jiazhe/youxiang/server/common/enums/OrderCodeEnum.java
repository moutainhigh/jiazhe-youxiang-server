package com.jiazhe.youxiang.server.common.enums;

/**
 * @author tu
 * @description：订单相关
 * @date 2018/11/8
 */
public enum OrderCodeEnum {

    ORDER_CAN_NOT_CANCEL(108001, "ORDER_CAN_NOT_CANCEL", "订单不能被取消"),
    ORDER_CAN_CALCULATE_NEED_PAY(108002, "ORDER_CAN_CALCULATE_NEED_PAY","该订单不支持计算待付金额"),
    ORDER_CAN_NOT_CHECK(108003, "ORDER_CAN_NOT_CHECK", "订单不是待审核状态，不能被审核"),
    CUSTOMER_NOT_EXIST(108004, "CUSTOMER_NOT_EXIST", "客户不存在"),
    PRODUCT_NOT_AVAILABLE(108005, "PRODUCT_NOT_AVAILABLE", "该商品不可在该城市下下单"),
    ORDER_VOUCHER_PAY_ERROR(108006, "ORDER_VOUCHER_PAY_ERROR", "代金券支付信息有误"),
    ORDER_RECHARGE_CARD_PAY_ERROR(108007, "ORDER_RECHARGE_CARD_PAY_ERROR", "充值卡支付信息有误"),
    VOUCHER_NOT_SUPPORT_PRODUCT(108008, "VOUCHER_NOT_SUPPORT_PRODUCT", "代金券不支持该商品"),
    VOUCHER_NOT_SUPPORT_CITY(108009, "VOUCHER_NOT_SUPPORT_CITY", "代金券不支持该城市"),
    RECHARGE_CARD_NOT_SUPPORT_PRODUCT(108010, "RECHARGE_CARD_NOT_SUPPORT_PRODUCT", "充值卡不支持该商品"),
    RECHARGE_CARD_NOT_SUPPORT_CITY(108011, "RECHARGE_CARD_NOT_SUPPORT_CITY", "充值卡不支持该城市"),
    ORDER_STATUS_NOT_UNSENT(108012, "ORDER_STATUS_NOT_UNSENT", "订单不是待派单状态，不能预约"),
    ORDER_NOT_EXIST(108013, "ORDER_NOT_EXIST", "订单不存在"),
    ORDER_CAN_NOT_APPEND_ANOTHER(108014, "ORDER_CAN_NOT_APPEND_ANOTHER", "该订单不能追加新订单"),
    VOUCHER_IS_NOT_YOURS(108015, "VOUCHER_IS_NOT_YOURS", "不能使用他人名下的代金券"),
    RECHARGE_CARD_IS_NOT_YOURS(108016, "RECHARGE_CARD_IS_NOT_YOURS", "不能使用他人名下的充值卡"),
    WORKER_NAME_IS_NAME(108017, "WORKER_NAME_IS_NAME", "服务人员不能为空"),
    WORKER_MOBILE_IS_NAME(108018, "WORKER_MOBILE_IS_NAME", "服务人员电话不能为空"),
    REAL_SERVICE_TIME_IS_NULL(108019, "REAL_SERVICE_TIME_IS_NULL", "服务时间不能为空"),
    ORDER_COST_IS_NULL(108020, "ORDER_COST_IS_NULL", "订单成本不能为空"),
    ;

    OrderCodeEnum(Integer code, String type, String message) {
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
