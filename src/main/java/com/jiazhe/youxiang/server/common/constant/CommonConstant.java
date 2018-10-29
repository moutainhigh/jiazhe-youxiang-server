/*
 * Copyright (c) 2017 maoyan.com
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
     * 业务失败异常类型，当用此类型返回异常时，当前请求会被标示成失败请求
     */
    public static final String BUSINESS_ERROR = "BUSINESS_ERROR";

    /**
     * 百分号
     */
    public static final String PERCENT = "%";

    /**
     * 一级城市级别
     */
    public static final Integer CITY_LEVEL_1 = 1;
    /**
     * 二级城市级别
     */
    public static final Integer CITY_LEVEL_2 = 2;
    /**
     * 三级城市级别
     */
    public static final Integer CITY_LEVEL_3 = 3;


    public static final Byte CODE_NOT_DELETED = Byte.valueOf("0");

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
     * 一天的毫秒数
     */
    public static final Integer ONE_DAY = 24 * 3600 *1000 ;

    /**
     * 短信有效时间
     */
    public static final Integer FIVE_MINUTES = 5 * 60 *1000 ;

    /**
     * 订单状态【1代付款，2待派单，3待服务，4已完成，5取消待审核，6取消审核未通过，7已取消】
     */
    public static final Integer ORDER_UNPAID = 1;
    public static final Integer ORDER_UNSENT = 2;
    public static final Integer ORDER_UNSERVICE = 3;
    public static final Integer ORDER_COMPLETE = 4;
    public static final Integer ORDER_CANCELWATINGCHECK = 5;
    public static final Integer ORDER_CANCELUNPASS = 6;
    public static final Integer ORDER_CANCEL = 7;





}