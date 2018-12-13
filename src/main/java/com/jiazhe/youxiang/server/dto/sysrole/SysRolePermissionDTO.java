package com.jiazhe.youxiang.server.dto.sysrole;

import com.jiazhe.youxiang.server.vo.BaseObject;

import java.util.Date;

/**
 * @author TU
 * @date 2018/10/19
 */
public class SysRolePermissionDTO extends BaseObject {

    private static final long serialVersionUID = -6896992881908407547L;

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
