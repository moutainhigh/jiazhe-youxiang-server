/*
 * Copyright (c) 2017 ue-link.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.dto.project;

import com.jiazhe.youxiang.server.vo.BaseObject;

import java.math.BigDecimal;

/**
 * 项目编辑DTO
 *
 * @author niexiao
 * @created 2018/10/24
 */
public class ProjectUpdateDTO extends BaseObject {

    private static final long serialVersionUID = -6227848372301346180L;
    /**
     * 项目ID
     */
    private Integer id;
    /**
     * 项目名称
     */
    private String name;
    /**
     * 项目描述信息
     */
    private String description;
    /**
     * 排序
     */
    private Integer priority;
    /**
     * 积分兑换比例
     */
    private BigDecimal pointConversionRate;

    /**
     * 项目状态
     */
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public BigDecimal getPointConversionRate() {
        return pointConversionRate;
    }

    public void setPointConversionRate(BigDecimal pointConversionRate) {
        this.pointConversionRate = pointConversionRate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}