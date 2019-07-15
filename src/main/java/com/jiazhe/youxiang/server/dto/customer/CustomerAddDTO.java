/*
 * Copyright (c) 2017 ue-link.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.dto.customer;

import com.jiazhe.youxiang.server.vo.BaseObject;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/10/23
 */
public class CustomerAddDTO extends BaseObject{

    private static final long serialVersionUID = -1014240146348590216L;
    /**
     * 手机号码
     */
    private String mobile;
    /**
     * 客户名称
     */
    private String name;
    /**
     * 客户备注
     */
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