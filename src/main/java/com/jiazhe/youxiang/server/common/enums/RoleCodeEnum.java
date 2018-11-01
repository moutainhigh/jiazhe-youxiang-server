package com.jiazhe.youxiang.server.common.enums;

/**
 * Created by TU on 2018/10/18.
 */
public enum RoleCodeEnum {

    ROLE_NAME_IS_NULL(102001, "ROLE_NAME_IS_NULL", "角色名不能为空"),
    ROLE_PRIORITY_ILLEAGE(102002, "PRIORITY_ILLEAGE", "排序字段非法"),
    ROLE_NAME_HAS_EXISTED(102003, "ROLE_NAME_HAS_EXISTED", "角色名已经存在"),
    ROLE_NOT_EXISTED(102004, "ROLE_NOT_EXISTED", "角色不存在"),
    ROLE_PERMISSION_NOTCHOOSE(102005, "NOTCHOOSE", "没有选择权限");

    RoleCodeEnum(Integer code, String type, String message) {
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
