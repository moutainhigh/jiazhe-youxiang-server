/*
 * Copyright (c) 2017 ue-link.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.vo.req.customer;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

/**
 * 用户注册Req
 *
 * @author niexiao
 * @created 2018/12/17
 */
public class CustomerRegisterReq extends BaseVO {

    private static final long serialVersionUID = -5414827016260083400L;
    @ApiModelProperty("手机号码,必填")
    private String mobile;
    @ApiModelProperty("客户名称,必填")
    private String name;

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
}