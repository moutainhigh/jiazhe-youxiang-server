package com.jiazhe.youxiang.server.vo.req.sysrole;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

/**
 *角色添加、修改请求参数
 * @author TU
 * Created by TU on 2018/10/18.
 */
public class RoleSaveReq extends BaseVO {

    private static final long serialVersionUID = 2874917878062407932L;

    @ApiModelProperty("角色id")
    private int id;

    @ApiModelProperty("角色名称")
    private String name;

    @ApiModelProperty("是否是管理员")
    private Byte isSuper;

    @ApiModelProperty("角色排序")
    private int priority;

    @ApiModelProperty("权限字符串，用逗号连接")
    private String permsStr;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getIsSuper() {
        return isSuper;
    }

    public void setIsSuper(Byte isSuper) {
        this.isSuper = isSuper;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getPermsStr() {
        return permsStr;
    }

    public void setPermsStr(String permsStr) {
        this.permsStr = permsStr;
    }
}
