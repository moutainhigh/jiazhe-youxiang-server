/*
 * Copyright (c) 2019
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.dto.chargeoff;

import java.util.Date;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2020-03-04
 */
public class ChargeOffQueryDTO {

    /**
     * 提交时间起
     */
    private Date submitterTimeBegin;

    /**
     * 提交时间止
     */
    private Date submitterTimeEnd;

    /**
     * 提交状态 0-未提交，1-已提交，null-全部
     */
    private Integer status;

    /**
     * 兑换城市code
     */
    private String cityCode;

    /**
     * 兑换类型 0-换商品，1-充积分
     */
    private Integer chargeOffType;

    /**
     * 提交者名称
     */
    private String submitterName;

    /**
     * 核销总积分
     */
    private Integer totalPoint;

    public Date getSubmitterTimeBegin() {
        return submitterTimeBegin;
    }

    public void setSubmitterTimeBegin(Date submitterTimeBegin) {
        this.submitterTimeBegin = submitterTimeBegin;
    }

    public Date getSubmitterTimeEnd() {
        return submitterTimeEnd;
    }

    public void setSubmitterTimeEnd(Date submitterTimeEnd) {
        this.submitterTimeEnd = submitterTimeEnd;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public Integer getChargeOffType() {
        return chargeOffType;
    }

    public void setChargeOffType(Integer chargeOffType) {
        this.chargeOffType = chargeOffType;
    }

    public String getSubmitterName() {
        return submitterName;
    }

    public void setSubmitterName(String submitterName) {
        this.submitterName = submitterName;
    }

    public Integer getTotalPoint() {
        return totalPoint;
    }

    public void setTotalPoint(Integer totalPoint) {
        this.totalPoint = totalPoint;
    }
}
