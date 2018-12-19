package com.jiazhe.youxiang.server.common.enums;

/**
 * @author TU
 * @description
 * @date 2018/10/29.
 */
public enum LoginCodeEnum {
    LOGIN_LOGININFO_INCOMPLETE(112001, "LOGININFO_INCOMPLETE", "用户名和密码不能为空"),
    LOGIN_USER_ILLEGAL(112002, "USER_ILLEGAL", "非法用户"),
    LOGIN_PASSWRLD_WRONG(112003, "PASSWRLD_WRONG", "密码错误"),
    LOGIN_DIFFERENT_CLIENT(112004, "DIFFERENT_CLIENT", "异客户端登录"),
    LOGIN_MOBILE_ILLEGAL(112005, "MOBILE_ILLEGAL", "电话号码非法"),
    LOGIN_SENDCODE_ERROR(112006, "SENDCODE_ERROR", "发送验证码失败，请稍后重试"),
    LOGIN_IDENTIFYING_CODE_EMPTY(112007, "IDENTIFYING_CODE_EMPTY", "验证码不能为空"),
    LOGIN_IDENTIFYING_CODE_ERROR(112008, "IDENTIFYING_CODE_ERROR", "验证码错误"),
    LOGIN_IDENTIFYING_CODE_EXPIRY(112009, "LOGIN_IDENTIFYING_CODE_EXPIRY", "验证码失效"),
    LOGIN_NOT_SIGNIN_IN(112010, "LOGIN_NOT_SIGNIN_IN", "未登录"),
    LOGIN_CUSTOMER_NOT_EXISTED(112011, "LOGIN_CUSTOMER_NOT_EXISTED", "不存在该用户，请先注册");;

    LoginCodeEnum(Integer code, String type, String message) {
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
