package com.jiazhe.youxiang.server.dto.sysuser;

import java.util.Date;

/**
 * @author tu
 * @description：
 * @date 2018/10/19
 */
public class UserWithRoleDTO {

    private Integer id;

    private String mobile;

    private String name;

    private String roleIds;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }
}
