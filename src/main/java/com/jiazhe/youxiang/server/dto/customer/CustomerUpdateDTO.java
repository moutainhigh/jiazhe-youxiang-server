/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.dto.customer;

/**
 * 客户信息修改DTO
 *
 * @author niexiao
 * @created 2018/10/23
 */
public class CustomerUpdateDTO {

    /**
     * 客户id
     */
    private Integer id;
    /**
     * 客户名称
     */
    private String name;
    /**
     * 客户备注
     */
    private String remark;

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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}