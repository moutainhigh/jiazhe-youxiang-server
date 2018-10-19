package com.jiazhe.youxiang.server.common.enums;

/**
 * Created by TU on 2018/10/18.
 */
public enum RoleCodeEnum {

    ROLE_INCOMPLETE_INFO(200001, "INCOMPLETE_INFO", "信息填写不完整"),
    ROLE_PRIORITY_ILLEAGE(200002, "PRIORITY_ILLEAGE", "排序字段非法"),
    ROLE_HAS_EXISTED(200003, "HAS_EXISTED", "角色已经存在"),
    ROLE_PERMISSION_NOTCHOOSE(200004, "NOTCHOOSE", "没有选择权限");

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
