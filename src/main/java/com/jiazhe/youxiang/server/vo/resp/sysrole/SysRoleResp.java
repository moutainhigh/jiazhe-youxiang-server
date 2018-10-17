package com.jiazhe.youxiang.server.vo.resp.sysrole;

import com.jiazhe.youxiang.server.vo.BaseObject;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by tujia on 2018/10/14.
 */
public class SysRoleResp extends BaseObject{

    @ApiModelProperty("接口请求返回代码")
    private String code;

    @ApiModelProperty("接口请求返回消息")
    private String msg;

    @ApiModelProperty("角色id")
    private int id;

    @ApiModelProperty("角色名称")
    private String name;

    @ApiModelProperty("是否是管理员")
    private String isSuper;

    @ApiModelProperty("角色排序")
    private int priority;

    @ApiModelProperty("权限字符串，用逗号连接")
    private String permsStr;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getPermsStr() {
        return permsStr;
    }

    public void setPermsStr(String permsStr) {
        this.permsStr = permsStr;
    }

    public String getIsSuper() {
        return isSuper;
    }

    public void setIsSuper(String isSuper) {
        this.isSuper = isSuper;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
