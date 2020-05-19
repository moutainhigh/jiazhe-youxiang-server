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
     * 大家保险token
   */
    public static Map djbxTokenMap = new HashMap<String,String>();

    static{
        djbxTokenMap.put("DJBX_DEFAULT_TOKEN","eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJDTVNBUFAiLCJpZCI6IjEiLCJuYW1lIjoicG9pbnRzIiwiaWF0IjoxNTg5ODY0NDIzLCJleHAiOjE1OTA0NjkyMjN9.m0TXVdT_PpcaSzhPVaDo35N_YWHLa9Q90dviXIg8moI");
    }

    /**
     * 消费统，本次上线积分商城为 A001
     */
    public final static String SYS_CODE = "A001";

    /**
     * 积分查询接口编码
     */
    public final static String TRANS_CODE_POINTS_QUERY = "JF0001";

    /**
     * 积分核销接口编码
     */
    public final static String TRANS_CODE_CONSUME_POINTS = "JF0002";

    /**
     返回标识码 00=成功； 01-失败； 99-系统级错误
     */
    public final static String RESULT_CODE_SUCCESS = "00";
    public final static String RESULT_CODE_FAIL = "01";
    public final static String RESULT_CODE_SYSTEM_ERROR = "99";
}
