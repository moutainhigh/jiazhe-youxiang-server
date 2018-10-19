package com.jiazhe.youxiang.server.vo.resp.sysrole;

import com.jiazhe.youxiang.server.vo.BaseObject;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author TU
 * @date 2018/10/19
 */
public class SysRoleResp extends BaseObject {

    private static final long serialVersionUID = -8161107313653321193L;
    
    @ApiModelProperty("角色id")
    private int id;

    @ApiModelProperty("角色名称")
    private String name;

    @ApiModelProperty("是否是管理员")
    private Byte isSuper;

    @ApiModelProperty("角色排序")
    private int priority;

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

    public Byte getIsSuper() {
        return isSuper;
    }

    public void setIsSuper(Byte isSuper) {
        this.isSuper = isSuper;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
