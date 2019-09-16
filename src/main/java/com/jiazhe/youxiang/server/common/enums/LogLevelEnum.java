/*
 * Copyright (c) 2017 ue-link.com
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
public enum LogLevelEnum {

    LEVEL_1(1, "一级"),
    LEVEL_2(2, "二级"),
    LEVEL_3(3, "三级");

    private Integer id;
    private String name;

    LogLevelEnum(Integer id, String name) {
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