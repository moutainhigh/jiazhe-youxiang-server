/*
 * Copyright (c) 2019
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.vo.req.chargeoff;

import com.jiazhe.youxiang.server.vo.req.PageSizeNumReq;
import io.swagger.annotations.ApiModelProperty;

/**
 * 模糊查询req
 *
 * @author niexiao
 * @created 2020-03-04
 */
public class ChargeOffFuzzyQueryReq extends PageSizeNumReq {

    private static final long serialVersionUID = -2282988511465085931L;
    @ApiModelProperty("提交时间起")
    private Long submitterTimeBegin;

    @ApiModelProperty("提交时间止")
    private Long submitterTimeEnd;

    @ApiModelProperty("提交状态 0-未提交，1-已提交，null-全部")
    private Integer stauts;

    @ApiModelProperty("提交者id")
    private Integer submitterId;

    @ApiModelProperty("查询条件")
    private String condition;

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
