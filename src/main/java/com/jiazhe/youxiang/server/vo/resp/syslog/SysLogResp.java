/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.vo.resp.syslog;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 日志信息Resp
 *
 * @author niexiao
 * @created 2018/10/17
 */
public class SysLogResp extends BaseVO {

    @ApiModelProperty("ID")
    private Integer id;
    @ApiModelProperty("操作动作")
    private String action;
    @ApiModelProperty("日志类型")
    private Integer type;
    @ApiModelProperty("操作人ID")
    private Integer operatorId;
    @ApiModelProperty("操作人姓名")
    private String operatorName;
    @ApiModelProperty("操作IP")
    private String ip;
    @ApiModelProperty("日志详情")
    private String detail;
    @ApiModelProperty("添加时间")
    private Date addTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}