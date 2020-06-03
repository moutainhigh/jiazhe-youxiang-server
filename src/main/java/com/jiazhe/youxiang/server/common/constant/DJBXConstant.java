/*
 * Copyright (c) 2019 橙谊科技
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.common.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tu
 * @version 1.0
 * @description 大家保险业务的常量
 * @created 2020-05-19 8:00
 */
public class DJBXConstant {

    /**
     * 大家保险环境
     */
    public static String[] DJBX_ENVIRONMENT = {"djbx-test", "djbx-online"};

    public final static String DJBX_TOKEN_DEFAULT_KEY = "DJBX_DEFAULT_TOKEN";
    public final static String DJBX_TOKEN_DEFAULT_VALUE = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJDTVNBUFAiLCJpZCI6IjEiLCJuYW1lIjoicG9pbnRzIiwiaWF0IjoxNTg5ODY0NDIzLCJleHAiOjE1OTA0NjkyMjN9.m0TXVdT_PpcaSzhPVaDo35N_YWHLa9Q90dviXIg8moI";

    /**
     * 大家保险token
     */
    public static Map djbxTokenMap = new HashMap<String, String>();

    static {
        djbxTokenMap.put(DJBX_TOKEN_DEFAULT_KEY, DJBX_TOKEN_DEFAULT_VALUE);
    }

    /**
     * 消费统，本次上线积分商城为 A001
     */
    public final static String SYS_CODE = "A001";

    /**
     * 积分查询接口编码
     */
    public final static String TRANS_CODE_QUERY_POINTS = "JF0001";

    /**
     * 积分核销接口编码
     */
    public final static String TRANS_CODE_CONSUME_POINTS = "JF0002";

    /**
     * 返回标识码 00=成功； 01-失败； 99-系统级错误
     */
    public final static String RESULT_CODE_SUCCESS = "00";
    public final static String RESULT_CODE_FAIL = "01";
    public final static String RESULT_CODE_SYSTEM_ERROR = "99";

    /**
     * token过期代码
     */
    public final static String TOKEN_INVALID_CODE = "401";

    /**
     * 获取token的appName，userName
     */
    public final static String APP_NAME = "points";
    public final static String USER_NAME = "points";

    /**
     * 大家保险订单号前缀
     */
    public final static String DJBX_ORDER_PREFIX = "DJBX_";

    /**
     * 交易类型 01-积分消费， 02-积分退回
     */
    public final static String DJBX_TRANSACTIONTYPE_CONSUME= "01";
    public final static String DJBX_TRANSACTIONTYPE_BACK = "02";

    /**
     * 是否需要与积分商城结算  01-需要与积分商城结算，02-不需要与积分商城结算
     */
    public final static String DJBX_SETTLEMENTTYPE_NEED= "01";
    public final static String DJBX_SETTLEMENTTYPE_NOTNEED = "02";
}
