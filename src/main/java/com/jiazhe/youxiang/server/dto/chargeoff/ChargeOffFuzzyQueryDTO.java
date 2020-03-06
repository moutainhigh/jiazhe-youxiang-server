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
public class ChargeOffFuzzyQueryDTO {

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
    private Integer stauts;

    /**
     * 提交者id
     */
    private Integer submitterId;

    /**
     * 查询条件
     */
    private String condition;

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

    public Integer getStauts() {
        return stauts;
    }

    public void setStauts(Integer stauts) {
        this.stauts = stauts;
    }

    public Integer getSubmitterId() {
        return submitterId;
    }

    public void setSubmitterId(Integer submitterId) {
        this.submitterId = submitterId;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
