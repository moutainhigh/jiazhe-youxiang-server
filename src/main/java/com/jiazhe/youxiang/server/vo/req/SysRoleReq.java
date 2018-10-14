package com.jiazhe.youxiang.server.vo.req;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by tujia on 2018/10/14.
 */
public class SysRoleReq extends BaseVO {

    private static final long serialVersionUID = 8912041647153105287L;
    @ApiModelProperty("第几页")
    private int pageNum;
    @ApiModelProperty("每页数量")
    private int pageSize;
    @ApiModelProperty("角色名称")
    private String name;

    private int offset;
    private int limit;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOffset() {
        return pageSize*(pageNum-1);
    }

    public int getLimit() {
        return pageSize;
    }
}
