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
