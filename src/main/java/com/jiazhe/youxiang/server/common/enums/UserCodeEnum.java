package com.jiazhe.youxiang.server.common.enums;

/**
 * @author tu
 * @description：
 * @date 2018/10/20
 */
public enum UserCodeEnum {

    USER_INCOMPLETE_INFO(300001, "INCOMPLETE_INFO", "信息填写不完整"),
    USER_HAS_EXISTED(300003, "HAS_EXISTED", "用户已经存在"),
    USER_Role_NOTCHOOSE(200004, "NOTCHOOSE", "没有选择角色");

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
