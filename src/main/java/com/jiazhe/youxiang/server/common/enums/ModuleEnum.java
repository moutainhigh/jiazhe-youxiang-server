/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.common.enums;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/10/18
 */
public enum ModuleEnum {

    LOG(0, "日志管理"),
    REGISTER(1, "登录管理"),
    PRODUCT(2, "商品管理"),
    PROJECT(3, "项目管理"),
    RECORD(4, "订单管理"),
    CUSTOMER(5, "客户管理"),
    RECHARGE(6, "充值卡管理"),
    VOUCHER(7, "代金券管理"),
    CITY(8, "城市管理"),
    USER(9, "用户管理"),
    OTHER(99, "其他模块");

    private Integer id;
    private String name;

    ModuleEnum(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

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


}