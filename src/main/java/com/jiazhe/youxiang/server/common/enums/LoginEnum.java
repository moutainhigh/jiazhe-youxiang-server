package com.jiazhe.youxiang.server.common.enums;

/**
 * @author TU
 * @description
 * @date 2018/10/29.
 */
public enum LoginEnum {
    LOGIN_LOGININFO_INCOMPLETE(400001, "LOGININFO_INCOMPLETE", "用户名和密码不能为空"),
    LOGIN_USER_ILLEGAL(400002, "USER_ILLEGAL", "非法用户"),
    LOGIN_PASSWRLD_WRONG(400003, "PASSWRLD_WRONG", "密码错误"),
    LOGIN_DIFFERENT_CLIENT(400004, "DIFFERENT_CLIENT", "异客户端登录"),
    LOGIN_MOBILE_ILLEGAL(400005, "MOBILE_ILLEGAL", "绑定的电话号码非法，请联系管理员"),
    LOGIN_SENDCODE_ERROR(400006, "SENDCODE_ERROR", "发送验证码失败，请稍后重试"),
    LOGIN_IDENTIFYING_CODE_EMPTY(400007, "IDENTIFYING_CODE_EMPTY", "验证码不能为空"),
    LOGIN_IDENTIFYING_CODE_ERROR(400008, "IDENTIFYING_CODE_ERROR", "验证码错误");

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
