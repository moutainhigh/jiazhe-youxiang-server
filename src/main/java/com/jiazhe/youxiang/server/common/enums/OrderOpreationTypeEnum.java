package com.jiazhe.youxiang.server.common.enums;

/**
 * @Description: 订单操作类型枚举
 * @Author: zwx
 * @CreateDate: 2019/11/23 12:41
 */
public enum OrderOpreationTypeEnum {

    OTHER(0, "other", "其他"),
    CREATE(1, "create", "新建订单"),
    UPDATE(2, "update", "修改预约信息"),
    CANCEL(3, "cancel", "取消订单"),
    COMPLETE(4, "complete", "完成订单"),
    PASS(5, "pass", "审核通过"),
    UNPASS(6, "unpass", "审核未通过"),
    RESERVATION(7, "reservation", "预约服务"),
    APPEND(8, "append", "追加订单"),
    PARTNER_CREATE(9, "append", "新建商家订单"),
    PARTNER_UPDATE(10, "append", "修改商家订单");

    OrderOpreationTypeEnum(Integer code, String type, String message) {
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

    public static OrderOpreationTypeEnum getByCode(Integer code) {
        for (OrderOpreationTypeEnum item : OrderOpreationTypeEnum.values()) {
            if (item.getCode().equals(code))
                return item;
        }
        return null;
    }
}
