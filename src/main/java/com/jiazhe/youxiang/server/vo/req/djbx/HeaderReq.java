/*
 * Copyright (c) 2019 橙谊科技
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.vo.req.djbx;

import com.jiazhe.youxiang.base.util.DateUtil;
import com.jiazhe.youxiang.server.vo.BaseVO;

import java.util.Date;

/**
 * @author tu
 * @version 1.0
 * @description 大家保险的<header>报文头
 * @created 2020-05-18 22:30
 */
public class HeaderReq extends BaseVO{

    public HeaderReq(String serialNo,String transCode,String sysCode){
        this.serialNo = sysCode + serialNo;
        this.transCode = transCode;
        String nowStr = DateUtil.secondToStr(new Date());
        this.transDate = nowStr.substring(0,10);
        this.transTime = nowStr.substring(11,19);
        this.sysCode = sysCode;
    }

    private String serialNo;

    private String transCode;

    private String transDate;

    private String transTime;

    private String sysCode;

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getTransCode() {
        return transCode;
    }

    public void setTransCode(String transCode) {
        this.transCode = transCode;
    }

    public String getTransDate() {
        return transDate;
    }

    public void setTransDate(String transDate) {
        this.transDate = transDate;
    }

    public String getTransTime() {
        return transTime;
    }

    public void setTransTime(String transTime) {
        this.transTime = transTime;
    }

    public String getSysCode() {
        return sysCode;
    }

    public void setSysCode(String sysCode) {
        this.sysCode = sysCode;
    }

}
