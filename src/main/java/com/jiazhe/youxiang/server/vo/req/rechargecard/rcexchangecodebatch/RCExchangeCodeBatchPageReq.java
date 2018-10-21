package com.jiazhe.youxiang.server.vo.req.rechargecard.rcexchangecodebatch;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author tu
 * @description：充值卡兑换码批次（分页）请求参数
 * @date 2018/10/21
 */
public class RCExchangeCodeBatchPageReq extends BaseVO{

    private static final long serialVersionUID = -894330229162919879L;

    @ApiModelProperty("项目id")
    private Integer projectId;

    @ApiModelProperty("批次名称")
    private String name;

    private Integer pageSize;

    private Integer pageNum;

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
