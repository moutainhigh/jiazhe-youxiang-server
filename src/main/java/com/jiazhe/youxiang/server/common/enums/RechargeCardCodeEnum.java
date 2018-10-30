package com.jiazhe.youxiang.server.common.enums;

/**
 * @author TU
 * @description
 * @date 2018/10/30.
 */
public enum RechargeCardCodeEnum {

    INFO_INCOMPLETE(106001, "INFO_INCOMPLETE", "信息填写不完整"),
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
