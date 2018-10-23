package com.jiazhe.youxiang.server.vo.req.sysuser;

import com.jiazhe.youxiang.server.vo.BaseVO;

/**
 * @author tu
 * @description：
 * @date 2018/10/19
 */
public class UserSaveReq extends BaseVO {

    private Integer id;

    private String mobile;

    private String name;

    private String password;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }

}
