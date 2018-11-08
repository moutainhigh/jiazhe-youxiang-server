package com.jiazhe.youxiang.server.common.enums;

/**
 * @author tu
 * @description：订单相关
 * @date 2018/11/8
 */
public enum OrderCodeEnum {

    ORDER_CAN_NOT_CANCEL(108001, "订单不能被取消", "ORDER_CAN_NOT_CANCEL"),
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
