package com.jiazhe.youxiang.server.vo.req;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author TU
 * @description
 * @date 2018/10/22.
 */
public class   PageSizeNumReq extends BaseVO {

    @NotNull
    @Min(1)
    @ApiModelProperty("每页显示数量")
    private Integer pageSize;

    @NotNull
    @Min(1)
    @ApiModelProperty("当前页码")
    private Integer pageNum;

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
