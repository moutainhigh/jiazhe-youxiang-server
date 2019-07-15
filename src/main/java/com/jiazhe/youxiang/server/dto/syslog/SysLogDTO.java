/*
 * Copyright (c) 2017 ue-link.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.dto.syslog;

import com.jiazhe.youxiang.server.vo.BaseObject;

import java.util.Date;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/10/17
 */
public class SysLogDTO extends BaseObject{

    private static final long serialVersionUID = 2789376780207198013L;
    /**
     * ID
     */
    private Integer id;
    /**
     * 模块名称
     */
    private String moduleName;

    /**
     * 操作
     */
    private String operate;

    /**
     * 日志级别
     */
    private Integer level;
    /**
     * 操作人ID
     */
    private Integer operatorId;
    /**
     * 操作人姓名
     */
    private String operatorName;
    /**
     * 操作IP
     */
    private String ip;
    /**
     * 日志详情
     */
    private String detail;
    /**
     * 添加时间
     */
    private Date addTime;

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

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}