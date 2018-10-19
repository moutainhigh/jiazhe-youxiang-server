package com.jiazhe.youxiang.server.vo.req.sysrole;

import com.jiazhe.youxiang.server.vo.req.OffsetLimitReq;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author TU
 * @description 分页查询参数
 * @date 2018/10/19.
 */
public class RolePageReq extends OffsetLimitReq {

    private static final long serialVersionUID = 6694925201108152597L;

    @ApiModelProperty("角色名称")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
