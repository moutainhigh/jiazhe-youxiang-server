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
    @ApiModelProperty("偏移量")
    private int offset;
    @ApiModelProperty("条数")
    private int limit;

    @ApiModelProperty("角色id")
    private int id;
    @ApiModelProperty("角色名称")
    private String name;
    @ApiModelProperty("是否是管理员")
    private String isSuper;
    @ApiModelProperty("排序")
    private int priority;
    @ApiModelProperty("权限字符串，用逗号连接")
    private String perms;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsSuper() {
        return isSuper;
    }

    public void setIsSuper(String isSuper) {
        this.isSuper = isSuper;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }
}