/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.common.constant;

import java.math.BigDecimal;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/10/15
 */
public class CommonConstant {

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
     * 订单号前缀
     */
    public static final String ORDER_CODE_PREFIX = "2";

    /**
     * 一天的毫秒数
     */
    public static final long ONE_DAY = 24 * 3600 * 1000;

    /**
     * 短信有效时间
     */
    public static final Integer FIVE_MINUTES = 5 * 60 * 1000;

    /**
     * 订单状态【1代付款，2待派单，3待服务，4已完成，5取消待审核，6取消审核未通过，7已取消】
     */
    public static final Byte ORDER_UNPAID = 1;
    public static final Byte ORDER_UNSENT = 2;
    public static final Byte ORDER_UNSERVICE = 3;
    public static final Byte ORDER_COMPLETE = 4;
    public static final Byte ORDER_CANCELWATINGCHECK = 5;
    public static final Byte ORDER_CANCELUNPASS = 6;
    public static final Byte ORDER_CANCEL = 7;

    public static final Byte BATCH_IS_VIRTUAL = Byte.valueOf("1");

    /**
     * 积分卡过期时间来源,0为直接指定过期时间，1为指定兑换之日起有效期天数
     */
    public static final Byte POINT_EXPIRY_TIME = Byte.valueOf("0");
    public static final Byte POINT_EXPIRY_PERIOD = Byte.valueOf("1");

    /**
     * 充值卡过期时间来源,0为直接指定过期时间，1为指定兑换之日起有效期天数
     */
    public static final Byte RECHARGE_CARD_EXPIRY_TIME = Byte.valueOf("0");
    public static final Byte RECHARGE_CARD_EXPIRY_PERIOD = Byte.valueOf("1");

    /**
     * 优惠券过期时间来源,0为直接指定过期时间，1为指定兑换之日起有效期天数
     */
    public static final Byte VOUCHER_EXPIRY_TIME = Byte.valueOf("0");
    public static final Byte VOUCHER_EXPIRY_PERIOD = Byte.valueOf("1");

    /**
     * 启用停用状态
     */
    public static final Byte STARTUSING = Byte.valueOf("1");
    public static final Byte STOPTUSING = Byte.valueOf("0");

    /**
     * 兑换方式【0-后台兑换码兑换，1-客户自行兑换码兑换，2-直接充值，3审核系统来的】
     */
    public static final Integer EXCHANGETYPE_USER_CODE_EXCHANGE = 0;
    public static final Integer EXCHANGETYPE_CUSTOMER_CODE_EXCHANGE = 1;
    public static final Integer EXCHANGETYPE_USER_DIRECTCHARGE = 2;
    public static final Integer EXCHANGETYPE_AUDITRECORD_PASS = 3;

    /**
     * 是否已经使用
     */
    public static final Byte CODE_NOT_USED = Byte.valueOf("0");
    public static final Byte CODE_HAS_USED = Byte.valueOf("1");

    /**
     * 批次下的码是否已经制作
     */
    public static final Byte EXCHANGE_CODE_HAS_MADE = Byte.valueOf("1");
    public static final Byte EXCHANGE_CODE_NOT_MADE = Byte.valueOf("0");

    /**
     * 支付类型【1为充值卡支付，2为代金券支付，3为在线支付】
     */
    public static final Byte PAY_RECHARGE_CARD = Byte.valueOf("1");
    public static final Byte PAY_VOUCHER = Byte.valueOf("2");
    public static final Byte PAY_CASH = Byte.valueOf("3");

    /**
     * 商品类型【0为服务型商品，1为电子商品】
     */
    public static final Integer SERVICE_PRODUCT = 0;
    public static final Integer ELE_PRODUCT = 1;

    /**
     * 1积分 = 5 元 汇率
     */
    public static final BigDecimal exchangeRate = new BigDecimal(5) ;

}