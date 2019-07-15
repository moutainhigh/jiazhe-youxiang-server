/*
 * Copyright (c) 2017 ue-link.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.vo.resp.project;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/10/18
 */
public class ProjectResp extends BaseVO {

    private static final long serialVersionUID = -5175730103631057244L;
    @ApiModelProperty("项目ID")
    private Integer id;
    @ApiModelProperty("项目名称")
    private String name;
    @ApiModelProperty("项目描述信息")
    private String description;
    @ApiModelProperty("排序")
    private Integer priority;
    @ApiModelProperty("积分兑换比例")
    private Integer pointConversionRate;
    @ApiModelProperty("项目状态")
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

    public Integer getPointConversionRate() {
        return pointConversionRate;
    }

    public void setPointConversionRate(Integer pointConversionRate) {
        this.pointConversionRate = pointConversionRate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}