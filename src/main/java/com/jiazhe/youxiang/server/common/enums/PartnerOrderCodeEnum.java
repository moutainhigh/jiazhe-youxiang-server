package com.jiazhe.youxiang.server.common.enums;

/**
 * @author tu
 * @description：
 * @date 2018/12/15
 */
public enum PartnerOrderCodeEnum {

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
