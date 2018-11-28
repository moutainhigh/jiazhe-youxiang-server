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

    private static final long serialVersionUID = 7104557889087221747L;
    @ApiModelProperty("ID")
    private Integer id;
    @ApiModelProperty("模块名称")
    private String moduleName;
    @ApiModelProperty("操作")
    private String operate;
    @ApiModelProperty("日志级别")
    private Integer level;
    @ApiModelProperty("操作人ID")
    private Integer operatorId;
    @ApiModelProperty("操作人姓名")
    private String operatorName;
    @ApiModelProperty("操作IP")
    private String ip;
    @ApiModelProperty("日志详情")
    private String detail;
    @ApiModelProperty("添加时间")
    private Long addTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }
}