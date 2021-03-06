/*
 * Copyright (c) 2017 ue-link.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.common.constant;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/10/15
 */
public class CommonConstant {

    /**
     * 计数监控key
     */
    public static final String HTTP_API_REQ_COUNT = "http_api_req_count";
    /**
     * 耗时监控key
     */
    public static final String HTTP_API_REQ_DURATION = "http_api_req_duration";
    /**
     * 请求成功code
     */
    public static final String CODE_SUCCEED = "0";
    /**
     * 请求失败code
     */
    public static final String CODE_NOT_SUCCEED = "1";
    /**
     * 请求内部异常code
     */
    public static final String CODE_INTERNAL_ERROR = "2";


    /**
     * 业务失败异常类型，当用此类型返回异常时，当前请求会被标示成失败请求
     */
    public static final String BUSINESS_ERROR = "BUSINESS_ERROR";

    /**
     * 百分号
     */
    public static final String PERCENT = "%";

    /**
     * 城市开通的代码
     */
    public static final Byte CODE_CITY_OPEN = Byte.valueOf("1");
    /**
     * 没有被删除的代码
     */
    public static final Byte CODE_NOT_DELETED = Byte.valueOf("0");

    /**
     * 数据被删除的代码
     */
    public static final Byte CODE_DELETED = Byte.valueOf("1");

    public static final String DEFAULT_TAG_CLIENT_IP = "clientIp";

    /**
     * 充值卡兑换码前缀
     */
    public static final String RC_EXCHANGE_CODE_PREFIX = "0";

    /**
     * 代金券兑换码前缀
     */
    public static final String VOUCHER_EXCHANGE_CODE_PREFIX = "1";

    /**
     * 积分兑换码前缀
     */
    public static final String POINT_EXCHANGE_CODE_PREFIX = "2";

    /**
     * 时间相关的固定值
     */
    public static final long ONE_DAY = 24L * 3600 * 1000;
    public static final long NEVER = -1L;
    public static final long ONE_HOUR = 3600L * 1000;
    public static final long EIGHT_HOUR = 8L * 3600 * 1000;
    public static final long THREE_MONTH = 3L * 30 * 24 * 3600 * 1000;
    public static final long ONE_YEAR = 365L * 24 * 3600 * 1000;

    /**
     * 短信有效时间5分钟
     */
    public static final long FIVE_MINUTES = 5L * 60 * 1000;

    /**
     * 前台时间未选择，传过来的固定值为0
     */
    public static final long NULL_TIME = 0L;

    /**
     * 订单状态【1代付款，2待派单，3待服务，4已完成，5取消待审核，6取消审核未通过，7已取消】
     */
    public static final Byte ORDER_ALL = 0;
    public static final Byte ORDER_UNPAID = 1;
    public static final Byte ORDER_UNSENT = 2;
    public static final Byte ORDER_UNSERVICE = 3;
    public static final Byte ORDER_COMPLETE = 4;
    public static final Byte ORDER_CANCELWATINGCHECK = 5;
    public static final Byte ORDER_CANCELUNPASS = 6;
    public static final Byte ORDER_CANCEL = 7;

    /**
     * 商家订单状态【1待派单 2待服务，3已完成，4已取消】
     */
    public static final Byte PARTNER_ORDER_UNSENT = 1;
    public static final Byte PARTNER_ORDER_UNSERVICE = 2;
    public static final Byte PARTNER_ORDER_COMPLETE = 3;
    public static final Byte PARTNER_ORDER_CANCEL = 4;

    public static final Byte BATCH_IS_VIRTUAL = Byte.valueOf("1");

    /**
     * 积分卡过期时间来源,0为指定时间，1为自兑换之日起，2为自激活之日起
     */
    public static final Byte POINT_EXPIRY_TIME = Byte.valueOf("0");
    public static final Byte POINT_EXCHANGE_PERIOD = Byte.valueOf("1");
    public static final Byte POINT_ACTIVE_PERIOD = Byte.valueOf("2");

    /**
     * 充值卡过期时间来源,0为指定时间，1为自兑换之日起，2为自激活之日起
     */
    public static final Byte RECHARGE_CARD_EXPIRY_TIME = Byte.valueOf("0");
    public static final Byte RECHARGE_CARD_EXCHANGE_PERIOD = Byte.valueOf("1");
    public static final Byte RECHARGE_CARD_ACTIVE_PERIOD = Byte.valueOf("2");

    /**
     * 代金券过期时间来源,0为指定时间，1为自兑换之日起，2为自激活之日起
     */
    public static final Byte VOUCHER_EXPIRY_TIME = Byte.valueOf("0");
    public static final Byte VOUCHER_EXCHANGE_PERIOD = Byte.valueOf("1");
    public static final Byte VOUCHER_ACTIVE_PERIOD = Byte.valueOf("2");

    /**
     * 启用停用状态
     */
    public static final Byte CODE_START_USING = Byte.valueOf("1");
    public static final Byte CODE_STOP_USING = Byte.valueOf("0");

    /**
     * 兑换码是否生成
     */
    public static final Byte CODE_HAS_MADE = Byte.valueOf("1");
    public static final Byte CODE_NOT_MADE = Byte.valueOf("0");

    /**
     * 兑换方式【0-后台兑换码兑换，1-客户自行兑换码兑换，2-直接充值，3-审核系统来的,4-二维码兑换,5-核销兑换】
     */
    public static final Integer EXCHANGETYPE_USER_CODE_EXCHANGE = 0;
    public static final Integer EXCHANGETYPE_CUSTOMER_CODE_EXCHANGE = 1;
    public static final Integer EXCHANGETYPE_USER_DIRECTCHARGE = 2;
    public static final Integer EXCHANGETYPE_AUDITRECORD_PASS = 3;
    public static final Integer EXCHANGETYPE_QRCODE_EXCHANGE = 4;
    public static final Integer EXCHANGETYPE_QRCODE_CHARGE_OFF = 5;

    /**
     * 0未使用 1已使用 2已退货
     */
    public static final Byte CODE_NOT_USED = Byte.valueOf("0");
    public static final Byte CODE_HAS_USED = Byte.valueOf("1");
    public static final Byte CODE_HAS_REFUND = Byte.valueOf("2");

    /**
     * 批次下的码是否已经制作
     */
    public static final Byte EXCHANGE_CODE_HAS_MADE = Byte.valueOf("1");
    public static final Byte EXCHANGE_CODE_NOT_MADE = Byte.valueOf("0");

    /**
     * 支付类型【1为积分卡支付，2为充值卡支付，3为代金券支付，4为在线支付 5大家保险积分】
     */
    public static final Byte PAY_POINT = Byte.valueOf("1");
    public static final Byte PAY_RECHARGE_CARD = Byte.valueOf("2");
    public static final Byte PAY_VOUCHER = Byte.valueOf("3");
    public static final Byte PAY_CASH = Byte.valueOf("4");
    public static final Byte PAY_DJBX = Byte.valueOf("5");

    /**
     * 商品类型【0为服务型商品，1为电子商品】
     */
    public static final Integer SERVICE_PRODUCT = 0;
    public static final Integer ELE_PRODUCT = 1;

    /**
     * 一个小时内的订单上限，生成订单号的时候用到
     */
    public static final Integer ORDER_CEILING_PER_HOUR = 999;

    /**
     * 每个账户允许的ip白名单上限
     */
    public static final Integer IP_WHITE_LIST_UPPER_LIMIT = 5;

    /**
     * 审核消费记录状态 【1未提交，2已提交，3已驳回，4已通过】
     */
    public static final Byte AUDIT_RECORD_NOT_SUBMITTED = Byte.valueOf("1");
    public static final Byte AUDIT_RECORD_HAS_SUBMITTED = Byte.valueOf("2");
    public static final Byte AUDIT_RECORD_REJECT = Byte.valueOf("3");
    public static final Byte AUDIT_RECORD_PASS = Byte.valueOf("4");

    /**
     * 小程序提交记录状态【1直接充积分，2拿积分卡自行充值，3兑换实物，4是预采购】
     */
    public static final Byte DIRECT_CHARGE = Byte.valueOf("1");
    public static final Byte SELF_CHARGE = Byte.valueOf("2");
    public static final Byte EXCHANGE_ENTITY = Byte.valueOf("3");
    public static final Byte PRE_PURCHASE = Byte.valueOf("4");

    /**
     * 下单方式【0后台员工下单  1客户自己下单 2大家保险员工下单】
     */
    public static final Byte USER_PLACE_ORDER = Byte.valueOf("0");
    public static final Byte CUSTOMER_PLACE_ORDER = Byte.valueOf("1");
    public static final Byte DJBX_PLACE_ORDER = Byte.valueOf("2");

    /**
     * 消费凭证录入状态【0未完成  1已完成】
     */
    public static final Byte CHARGE_RECEIPT_UNCOMPLETE = Byte.valueOf("0");
    public static final Byte CHARGE_RECEIPT_COMPLETE = Byte.valueOf("1");

    /**
     * 生成redis key的连接符号
     */
    public static final String REDIS_KEY_CONNECT = ":";

    /**
     * redis中key的前缀
     * REDIS_VER_CODE 短信验证码
     * REDIS_PERM_URL 权限字符串
     */
    public static final String REDIS_VER_CODE = "redis_ver_code" + REDIS_KEY_CONNECT;
    public static final String REDIS_PERM_URL = "redis_perm_url" + REDIS_KEY_CONNECT;

    /**
     * 短信服务商 1腾讯 2阿里
     */
    public static final Byte MSG_SERVICE_PROVIDER_TENCENT = Byte.valueOf("1");
    public static final Byte MSG_SERVICE_PROVIDER_ALI = Byte.valueOf("2");

    /**
     * 短信类型 1其他 2业务 3营销 4验证码
     */
    public static final Byte MSG_TYPE_OTHERS = Byte.valueOf("1");
    public static final Byte MSG_TYPE_BUSINESS = Byte.valueOf("2");
    public static final Byte MSG_TYPE_MARKETING = Byte.valueOf("3");
    public static final Byte MSG_TYPE_VER_CODE = Byte.valueOf("4");

    public static final int VER_CODE_LENGTH = 4;

    /**
     * 中行储蓄卡使用状态核对实时接口，使用状态
     */
    public static final String CODE_USE_STATUS_UNUSED = "00";
    public static final String CODE_USE_STATUS_USED = "01";

}
