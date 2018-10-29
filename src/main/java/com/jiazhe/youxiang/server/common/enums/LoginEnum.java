package com.jiazhe.youxiang.server.common.enums;

/**
 * @author TU
 * @description
 * @date 2018/10/29.
 */
public enum LoginEnum {

    LOGIN_USER_ILLEGAL(400001, "USER_ILLEGAL", "用户非法"),
    LOGIN_PASSWRLD_WRONG(400002, "PASSWRLD_WRONG", "密码错误"),
    LOGIN_DIFFERENT_CLIENT(400003, "DIFFERENT_CLIENT", "异客户端登录"),
    LOGIN_IDENTIFYING_CODE_ERROR(400003, "IDENTIFYING_CODE_ERROR", "验证码错误");

    LoginEnum(Integer code, String type, String message) {
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
