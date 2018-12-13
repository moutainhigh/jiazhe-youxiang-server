/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.vo.req.project;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/10/24
 */
public class ProjectAddReq extends BaseVO {

    private static final long serialVersionUID = 6481364365071703120L;
    @ApiModelProperty("项目名称,非空")
    private String name;
    @ApiModelProperty("项目描述信息，可空")
    private String description;
    @ApiModelProperty("排序，可空，默认1")
    private Integer priority = 1;
    @ApiModelProperty("积分兑换比例，默认5")
    private Integer pointConversionRate = 5;
    @ApiModelProperty("项目状态：0:未启动，1：进行中，2：已结束")
    private Integer status;

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