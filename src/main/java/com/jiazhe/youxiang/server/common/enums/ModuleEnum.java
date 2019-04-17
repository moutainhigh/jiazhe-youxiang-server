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
    USER(101, "用户管理"),
    ROLE(102, "权限管理"),
    CITY(103, "城市管理"),
    LOG(104, "日志管理"),
    PROJECT(105, "项目管理"),
    RECHARGE(106, "充值卡管理"),
    VOUCHER(107, "代金券管理"),
    ORDER(108, "订单管理"),
    CUSTOMER(109, "客户管理"),
    PRODUCT(110, "商品管理"),
    AUDIT_RECORD(111, "审核管理"),
    REGISTER(112, "登录管理"),
    ELE_PRODUCT(113, "商品电子码管理"),
    UPLOAD(114, "文件上传管理"),
    WECHAT_PAY(115, "微信支付相关"),
    POINT(116, "积分管理"),
    PARTNER_ORDER(117,"商家订单管理"),
    WECHAT_PUBLIC(118,"微信公众号相关"),
    CHARGE_RECEIPT(119,"消费凭证管理"),
    MESSAGE(120,"短信管理"),
    OTHER(999, "其他模块");

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