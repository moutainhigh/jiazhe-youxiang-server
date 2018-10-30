package com.jiazhe.youxiang.server.common.enums;

/**
 * @author TU
 * @description
 * @date 2018/10/30.
 */
public enum RechargeCardCodeEnum {

    INFO_INCOMPLETE(106001, "INFO_INCOMPLETE", "信息填写不完整"),
    VIRTUAL_BATCH_CANNOT_GENERATE(106002, "VIRTUAL_BATCH_CANNOT_GENERATE", "虚拟批次不能生成兑换码"),
    CODE_GENERATED(106003, "CODE_GENERATED", "兑换码已经生成，不能再次生成"),
    ;

    RechargeCardCodeEnum(Integer code, String type, String message) {
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
