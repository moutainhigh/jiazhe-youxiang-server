package com.jiazhe.youxiang.server.common.enums;

/**
 * @author TU
 * @description 发送短信的异常
 * @date 2019-04-08.
 */
public enum MessageCodeEnum {
    MOBILE_ILLEGAL(120001,"MOBILE_ILLEGAL","电话号码非法"),
    CONTENT_IS_NULL(120002,"CONTENT_IS_NULL","短信内容为空"),
    CONTENT_TEMPLATE_MISMATCH(120003,"CONTENT_TEMPLATE_MISMATCH","短信内容和所选模板不匹配"),
    TEMPLATE_IS_NOT_EXIST(120004,"TEMPLATE_IS_NOT_EXIST","短信模板不存在"),
    TEMPLATE_IS_STOP_USING(120005,"TEMPLATE_IS_STOP_USING","短信模板已经停用"),
    ;


    MessageCodeEnum(Integer code, String type, String message) {
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
