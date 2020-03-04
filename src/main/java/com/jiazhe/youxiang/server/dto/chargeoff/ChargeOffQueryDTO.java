/*
 * Copyright (c) 2019
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.dto.chargeoff;

import io.swagger.annotations.ApiModelProperty;

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
    private Long submitterTimeBegin;

    /**
     * 提交时间止
     */
    private Long submitterTimeEnd;

    /**
     * 提交状态 0-未提交，1-已提交，null-全部
     */
    private Integer stauts;

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

    public Long getSubmitterTimeBegin() {
        return submitterTimeBegin;
    }

    public void setSubmitterTimeBegin(Long submitterTimeBegin) {
        this.submitterTimeBegin = submitterTimeBegin;
    }

    public Long getSubmitterTimeEnd() {
        return submitterTimeEnd;
    }

    public void setSubmitterTimeEnd(Long submitterTimeEnd) {
        this.submitterTimeEnd = submitterTimeEnd;
    }

    public Integer getStauts() {
        return stauts;
    }

    public void setStauts(Integer stauts) {
        this.stauts = stauts;
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
