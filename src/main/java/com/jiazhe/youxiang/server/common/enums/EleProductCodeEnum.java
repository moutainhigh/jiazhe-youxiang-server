package com.jiazhe.youxiang.server.common.enums;

/**
 * @author TU
 * @description
 * @date 2018/11/12.
 */
public enum EleProductCodeEnum {

    FILE_NOT_EXIST(113001, "FILE_NOT_EXIST", "文件不存在"),
    FILE_FORMAT_ERROR(113002, "FILE_FORMAT_ERROR", "文件格式错误"),
    BATCH_NAME_IS_NULL(113003, "BATCHNAME_IS_NULL", "批次名为空"),
    EXPIRY_TIME_IS_NULL(113004, "EXPIRY_TIME_IS_NULL", "过期时间为空"),
    PRODUCT_IS_NULL(113005, "PRODUCT_IS_NULL", "对应商品为空"),
    ;

    EleProductCodeEnum(Integer code, String type, String message) {
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
