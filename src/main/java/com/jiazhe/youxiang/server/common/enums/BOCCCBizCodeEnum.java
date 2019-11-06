/*
 * Copyright (c) 2019
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.common.enums;

/**
 * @author TU
 * @description 中行信用卡
 * @date 2019-09-16.
 */
public enum BOCCCBizCodeEnum {

    SUCCESS("00", "可以退货/更新成功"),
    REFUND_AGAIN("01", "重复退货"),
    CODE_HAS_USED("02", "已使用"),
    CODE_NOT_EXIST("03", "券不存在"),
    CODE_EXPIRY("04", "券已过期"),
    DECRYPT_EXCEPTIOM("05", "解密异常"),
    DATA_FORMAT_EXCEPTION("06", "数据格式异常"),
    REFUNDING("07", "退货中"),
    ERROR("99", "操作失败"),;

    BOCCCBizCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private final String code;
    private final String message;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}