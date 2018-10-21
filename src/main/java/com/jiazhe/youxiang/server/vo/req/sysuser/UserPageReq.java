package com.jiazhe.youxiang.server.vo.req.sysuser;

import com.jiazhe.youxiang.server.vo.req.OffsetLimitReq;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author tu
 * @description：
 * @date 2018/10/19
 */
public class UserPageReq extends OffsetLimitReq {

    private static final long serialVersionUID = 1227970050086613351L;

    @ApiModelProperty("员工名称")
    private String name;

    private Integer pageSize;

    private Integer pageNum;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }
}
