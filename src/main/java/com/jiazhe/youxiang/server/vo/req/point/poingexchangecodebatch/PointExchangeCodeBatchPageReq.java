package com.jiazhe.youxiang.server.vo.req.point.poingexchangecodebatch;

import com.jiazhe.youxiang.server.vo.req.PageSizeNumReq;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author TU
 * @description  积分码充值卡批次分页请求信息
 * @date 2018/12/13.
 */
public class PointExchangeCodeBatchPageReq extends PageSizeNumReq{

    private static final long serialVersionUID = -4274930104962389243L;

    @ApiModelProperty("项目id")
    private Integer projectId;

    @ApiModelProperty("批次名称")
    private String name;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
