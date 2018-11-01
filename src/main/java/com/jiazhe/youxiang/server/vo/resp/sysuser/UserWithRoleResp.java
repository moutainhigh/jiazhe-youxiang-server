package com.jiazhe.youxiang.server.vo.resp.sysuser;

import com.jiazhe.youxiang.server.vo.BaseObject;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author tu
 * @description：
 * @date 2018/10/19
 */
public class UserWithRoleResp extends BaseObject {

    @ApiModelProperty("员工id")
    private Integer id;

    @ApiModelProperty("员工电话")
    private String mobile;

    @ApiModelProperty("员工登陆名")
    private String loginName;

    @ApiModelProperty("员工显示名")
    private String displayName;

    @ApiModelProperty("员工角色字符串，用逗号连接")
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

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }
}
