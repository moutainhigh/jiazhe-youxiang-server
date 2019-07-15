/*
 * Copyright (c) 2017 ue-link.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.dto.customer;

/**
 * 用户注册DTO
 *
 * @author niexiao
 * @created 2018/12/17
 */
public class CustomerRegisterDTO {

    /**
     * 手机号码
     */
    private String mobile;
    /**
     * 客户名称
     */
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