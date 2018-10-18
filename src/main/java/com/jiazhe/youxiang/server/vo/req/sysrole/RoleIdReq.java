package com.jiazhe.youxiang.server.vo.req.sysrole;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

/**
 * 角色回显请求参数
 * Created by TU on 2018/10/18.
 */
public class RoleIdReq extends BaseVO {

    private static final long serialVersionUID = -726832984775866466L;

    @ApiModelProperty("角色id")
    private Integer roleId;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
