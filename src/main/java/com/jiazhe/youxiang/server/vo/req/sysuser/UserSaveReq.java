package com.jiazhe.youxiang.server.vo.req.sysuser;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author tu
 * @description：
 * @date 2018/10/19
 */
public class UserSaveReq extends BaseVO {

    @ApiModelProperty("员工id，新增时为0")
    private Integer id;

    @ApiModelProperty("员工电话")
    private String mobile;

    @ApiModelProperty("员工姓名")
    private String name;

    @ApiModelProperty("员工登录密码")
    private String password;

    @ApiModelProperty("员工角色ids，用逗号连接")
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
