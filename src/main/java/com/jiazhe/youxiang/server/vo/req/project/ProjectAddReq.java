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
    private Integer name;
    @ApiModelProperty("项目描述信息，可空")
    private String description;
    @ApiModelProperty("排序，可空，默认1")
    private Integer priority = 1;
    @ApiModelProperty("项目状态,可空，默认关闭")
    private Byte status;

    public Integer getName() {
        return name;
    }

    public void setName(Integer name) {
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

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}