package com.jiazhe.youxiang.server.dto.sysuser;

import com.jiazhe.youxiang.server.vo.BaseObject;

/**
 * @author tu
 * @description：
 * @date 2018/10/19
 */
public class SysUserRoleDTO extends BaseObject {

    private static final long serialVersionUID = 5557152588900589210L;

    private Integer id;

    private Integer userId;

    private Integer roleId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
