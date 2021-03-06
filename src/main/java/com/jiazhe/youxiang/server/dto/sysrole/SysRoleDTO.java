/*
 * Copyright (c) 2017 ue-link.com
 * All rights reserved.
 *
 */
package com.jiazhe.youxiang.server.dto.sysrole;

import com.jiazhe.youxiang.server.vo.BaseObject;

import java.util.Date;

/**
 * 角色信息DTO
 * @author  tu
 * @date 2018/10/17
 */
public class SysRoleDTO extends BaseObject {

    private static final long serialVersionUID = 6305284462567729237L;

    private Integer id;

    private String name;

    private Byte isSuper;

    private Integer priority;

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

    public Byte getIsSuper() {
        return isSuper;
    }

    public void setIsSuper(Byte isSuper) {
        this.isSuper = isSuper;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

}