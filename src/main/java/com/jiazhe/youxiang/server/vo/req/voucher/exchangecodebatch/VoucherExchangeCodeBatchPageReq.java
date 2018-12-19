package com.jiazhe.youxiang.server.vo.req.voucher.exchangecodebatch;

import com.jiazhe.youxiang.server.vo.req.PageSizeNumReq;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author TU
 * @description
 * @date 2018/10/24.
 */
public class VoucherExchangeCodeBatchPageReq extends PageSizeNumReq {

    @ApiModelProperty("项目id")
    Integer projectId;

    @ApiModelProperty("批次名称")
    String name ;

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
