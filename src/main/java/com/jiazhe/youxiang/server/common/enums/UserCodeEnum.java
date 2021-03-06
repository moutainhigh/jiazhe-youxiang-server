package com.jiazhe.youxiang.server.common.enums;

/**
 * @author tu
 * @description：
 * @date 2018/10/20
 */
public enum UserCodeEnum {

    USER_LOGIN_NAME_IS_NULL(101001, "USER_LOGIN_NAME_IS_NULL", "登录名不能为空"),
    USER_DISPLAY_NAME_IS_NULL(101002, "USER_DISPLAY_NAME_IS_NULL", "姓名不能为空"),
    USEER_PASSWORD_IS_NULL(101003,"USEER_PASSWORD_IS_NULL","密码不能为空"),
    USEER_MOBILE_IS_NULL(101004,"USEER_MOBILE_IS_NULL","电话不能为空"),
    USEER_MOBILE_IS_ILLEGAL(101005,"USEER_MOBILE_IS_ILLEGAL","电话号码非法"),
    USER_HAS_EXISTED(101006, "HAS_EXISTED", "登录名不能重复"),
    USER_ROLE_NOT_CHOOSE(101007, "NOT_CHOOSE", "没有选择角色"),
    USER_PASSWORD_DIFFERENT(101008, "PASSWORD_DIFFERENT", "两次密码不一样"),
    USER_OLD_PASSWORD_WRONG(101009, "OLD_PASSWORD_WRONG", "密码错误"),
    USER_NOT_EXISTED(101010, "USER_NOT_EXISTED", "用户不存在");

    UserCodeEnum(Integer code, String type, String message) {
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
