package com.jiazhe.youxiang.server.common.enums;

/**
 * @author TU
 * @description
 * @date 2018/12/12.
 */
public enum WeChatPayCodeEnum {
    PRE_PAY_ERROR(115001, "PRE_PAY_ERROR", "发起支付失败"),
    GET_OPENID_ERROR(115002, "GET_OPENID_ERROR", "获取用户openid失败"),

            ;

    WeChatPayCodeEnum(Integer code, String type, String message) {
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
