package com.jiazhe.youxiang.server.dto.sysrole;

import java.util.Date;

/**
 * @author TU
 * Created by tujia on 2018/10/18.
 */
public class SysRolePermissionDTO {


    private Integer id;

    private Integer roleId;

    private String permUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getPermUrl() {
        return permUrl;
    }

    public void setPermUrl(String permUrl) {
        this.permUrl = permUrl;
    }

}
