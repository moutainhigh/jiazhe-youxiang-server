package com.jiazhe.youxiang.server.vo.req.sysuser;

import com.jiazhe.youxiang.server.vo.req.OffsetLimitReq;
import com.jiazhe.youxiang.server.vo.req.PageSizeNumReq;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author tu
 * @description：
 * @date 2018/10/19
 */
public class UserPageReq extends PageSizeNumReq {

    private static final long serialVersionUID = 1227970050086613351L;

    @ApiModelProperty("员工名称")
    private String name;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
