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
 * @created 2018/11/7
 */
public enum ProjectStatusEnum {

    TODO(0, "未启动"), DOING(1, "进行中"), FINISH(2, "已结束");

    private Integer id;
    private String name;

    ProjectStatusEnum(Integer id, String name) {
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