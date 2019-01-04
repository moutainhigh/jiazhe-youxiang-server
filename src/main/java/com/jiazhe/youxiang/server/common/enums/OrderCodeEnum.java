package com.jiazhe.youxiang.server.common.enums;

/**
 * @author tu
 * @description：订单相关
 * @date 2018/11/8
 */
public enum OrderCodeEnum {

    ORDER_CAN_NOT_CANCEL(108001, "ORDER_CAN_NOT_CANCEL", "订单不能被取消"),
    ORDER_CAN_CALCULATE_NEED_PAY(108002, "ORDER_CAN_CALCULATE_NEED_PAY", "该订单不支持计算待付金额"),
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
    ORDER_STATUS_NOT_UNSERVICE(108013, "ORDER_STATUS_NOT_UNSERVICE", "订单不是待服务状态，不能修改预约信息"),
    ORDER_NOT_EXIST(108014, "ORDER_NOT_EXIST", "订单不存在"),
    ORDER_CODE_REPEAT(108015, "ORDER_CODE_REPEAT", "订单号重复"),
    ORDER_CAN_NOT_APPEND_ANOTHER(108016, "ORDER_CAN_NOT_APPEND_ANOTHER", "该订单不能追加新订单"),
    VOUCHER_IS_NOT_YOURS(108017, "VOUCHER_IS_NOT_YOURS", "不能使用他人名下的代金券"),
    RECHARGE_CARD_IS_NOT_YOURS(108018, "RECHARGE_CARD_IS_NOT_YOURS", "不能使用他人名下的充值卡"),
    WORKER_NAME_IS_NAME(108019, "WORKER_NAME_IS_NAME", "服务人员不能为空"),
    WORKER_MOBILE_IS_NAME(108020, "WORKER_MOBILE_IS_NAME", "服务人员电话不能为空"),
    REAL_SERVICE_TIME_IS_NULL(108021, "REAL_SERVICE_TIME_IS_NULL", "服务时间不能为空"),
    ORDER_COST_IS_NULL(108022, "ORDER_COST_IS_NULL", "订单成本不能为空"),
    ELE_PRODUCT_CODE_NOT_ENOUGH(108023, "ELE_PRODUCT_CODE_NOT_ENOUGH", "商品库存不足，请联系管理员"),
    ORDER_COUNT_LESS_THAN_LAST_NUM(108024, "ORDER_COUNT_LESS_THAN_LAST_NUM", "下单数量不能少于最小下单数量"),
    ORDER_OVER_PAYMENT(108025, "ORDER_OVER_PAYMENT", "支付金额超过订单金额，支付失败"),
    SERVICE_TIME_ERROR(108026, "SERVICE_TIME_ERROR", "预约服务时间不符合要求"),
    ORDER_NO_GENERATE_ERROR(108027, "ORDER_NO_GENERATE_ERROR", "订单号生成错误"),
    POINT_IS_NOT_YOURS(108028, "POINT_IS_NOT_YOURS", "不能使用他人名下的积分卡"),
    POINT_CARD_PAY_ERROR(108029, "POINT_CARD_PAY_ERROR", "积分卡支付信息有误"),
    POINT_NOT_SUPPORT_PRODUCT(108030, "POINT_NOT_SUPPORT_PRODUCT", "积分卡不支持该商品"),
    POINT_NOT_SUPPORT_CITY(108031, "POINT_NOT_SUPPORT_CITY", "积分卡不支持该城市"),
    POINT_PAY_DECIMAL_APPEAR(108032, "POINT_PAY_DECIMAL_APPEAR", "积分卡支付出现小数"),
    ORDER_NOT_UNPAID(108033, "ORDER_NOT_UNPAID", "订单不是待支付状态，不能用微信支付"),
    WECHAT_PAY_FEE_ERROR(108034, "WECHAT_PAY_FEE_ERROR", "微信支付金额和订单不一致"),
    VOUCHER_IS_NOT_EFFECTIVE(108035,"VOUCHER_IS_NOT_EFFECTIVE" , "代金券还未生效，不能用于支付"),
    VOUCHER_IS_EXPIRY(108036,"VOUCHER_IS_EXPIRY" , "代金券已过期，不能用于支付"),
    POINT_IS_NOT_EFFECTIVE(108037,"POINT_IS_NOT_EFFECTIVE" , "积分卡还未生效，不能用于支付"),
    POINT_IS_EXPIRY(108038,"POINT_IS_EXPIRY" , "积分卡已过期，不能用于支付"),
    RECHARGE_CARD_IS_NOT_EFFECTIVE(108039,"RECHARGE_CARD_IS_NOT_EFFECTIVE" , "充值卡还未生效，不能用于支付"),
    RECHARGE_CARD_IS_EXPIRY(108040,"RECHARGE_CARD_IS_EXPIRY" , "充值卡已过期，不能用于支付"),
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
