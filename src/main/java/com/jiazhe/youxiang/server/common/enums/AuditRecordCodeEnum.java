package com.jiazhe.youxiang.server.common.enums;

/**
 * @author TU
 * @description
 * @date 2018/11/20.
 */
public enum AuditRecordCodeEnum {

    AUDIT_REASON_IS_NULL(111001, "AUDIT_REASON_IS_NULL", "审核理由不能为空"),
    VERSION_IS_CHANGED(111002, "VERSION_IS_CHANGED", "该条记录已经被修改，请刷新"),
    CUSTOMER_NOT_EXIST(111003, "CUSTOMER_NOT_EXIST", "该客户不存在，请先添加"),
    CUSTOMER_NAME_IS_NULL(111004, "CUSTOMER_NAME_IS_NULL", "客户姓名不能为空"),
    CUSTOMER_MOBILE_IS_NULL(111005, "CUSTOMER_MOBILE_IS_NULL", "客户电话不能为空"),
    EXCHANGE_POINT_IS_NULL(111006, "EXCHANGE_POINT_IS_NULL", "小票总积分不能为空"),
    GIVING_POINT_IS_NULL(111007, "GIVING_POINT_IS_NULL", "后台充值积分不能为空"),
    EXCHANGE_BATCH_IS_NULL(111008, "EXCHANGE_BATCH_IS_NULL", "兑换积分所属批次不能为空"),
    GIVING_BATCH_IS_NULL(111009, "GIVING_BATCH_IS_NULL", "赠送积分所属批次不能为空"),
    CUSTOMER_MOBILE_IS_ILLEGAL(111010, "CUSTOMER_MOBILE_IS_ILLEGAL", "客户电话非法"),
    RECORD_HASS_PASSED(111011, "RECORD_HASS_PASSED", "该条记录已经通过审核，不允许修改"),
    NO_BATCH_INFO(111012, "NO_BATCH_INFO", "没有指定批次"),
    TRADE_TIME_IS_NULL(111013, "TRADE_TIME_IS_NULL", "请选择交易时间"),
    POS_CODE_IS_NULL(111014, "POS_CODE_IS_NULL", "请填写POS机编号"),
    CARD_NO_IS_NULL(111015, "CARD_NO_IS_NULL", "请填写银行卡后四位"),
    BANK_NAME_IS_NULL(111016, "BANK_NAME_IS_NULL", "银行信息不能为空"),
    POINT_CODES_IS_NULL(111017, "POINT_CODES_IS_NULL", "发放的兑换码卡号不能为空"),
    PRODUCT_VALUE_IS_NULL(111018, "PRODUCT_VALUE_IS_NULL", "兑换的实物价值不能为空"),
    IMAGE_IS_NULL(111019, "IMAGE_IS_NULL", "请提供交易凭证"),
    AUDIT_RECORD_IS_NOT_EXIST(111020, "AUDIT_RECORD_IS_NOT_EXIST", "该条记录不存在"),
    POINT_CODES_ERROR(111021, "POINT_CODES_ERROR", "兑换码卡号有误"),
    AUDIT_RECORD_CANNOT_DELETE(111022,"AUDIT_RECORD_CANNOT_DELETE","该状态下的记录不能被删除"),
    EXCHANGE_TYPE_IS_NULL(111023,"EXCHANGE_TYPE_IS_NULL","兑换类型不能为空"),
    CANNOT_COMPLETE_CHARGE_RECEIPT(111024,"CANNOT_COMPLETE_CHARGE_RECEIPT","充值记录未审核通过，不能完成"),;

    AuditRecordCodeEnum(Integer code, String type, String message) {
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
