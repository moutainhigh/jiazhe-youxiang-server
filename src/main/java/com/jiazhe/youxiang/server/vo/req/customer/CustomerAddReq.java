/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.vo.req.customer;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/10/23
 */
public class CustomerAddReq extends BaseVO {

    private static final long serialVersionUID = -7970985863088405963L;
    @ApiModelProperty("手机号码,必填")
    private String mobile;
    @ApiModelProperty("客户名称,必填")
    private String name;
    @ApiModelProperty("客户备注,选填，可空")
    private String remark;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}