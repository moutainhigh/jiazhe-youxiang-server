/*
 * Copyright (c) 2019 橙谊科技
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.vo.resp.djbx;

import com.jiazhe.youxiang.server.vo.BaseObject;
import com.jiazhe.youxiang.server.vo.BaseVO;

import java.util.Date;

/**
 * @author tu
 * @version 1.0
 * @description 大家保险的返回<header>报文头
 * @created 2020-05-18 22:35
 */
public class HeaderResp extends BaseVO {

    private String resultCode;

    private String resultMessage;

    private Date transDate;

    private String transTime;

    private String serialNo;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public Date getTransDate() {
        return transDate;
    }

    public void setTransDate(Date transDate) {
        this.transDate = transDate;
    }

    public String getTransTime() {
        return transTime;
    }

    public void setTransTime(String transTime) {
        this.transTime = transTime;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

}
