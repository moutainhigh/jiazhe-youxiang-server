/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.dto.project;

import com.jiazhe.youxiang.server.vo.BaseObject;

/**
 * 在这里编写类的功能描述
 *
 * @author niexiao
 * @created 2018/10/24
 */
public class ProjectAddDTO extends BaseObject {

    private static final long serialVersionUID = 28694609692564715L;
    /**
     * 项目名称
     */
    private String name;
    /**
     * 项目描述信息
     */
    private String description;
    /**
     * 排序
     */
    private Integer priority;

    public String getName() {
        return name;
    }

    public void setName(String name) {
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
}