package com.jiazhe.youxiang.server.dto.sysrole;

import com.jiazhe.youxiang.server.vo.BaseObject;

/**
 * @author TU
 * @date 2018/10/19
 */
public class RoleWithPermDTO extends BaseObject {

    private Integer id;

    private String name;

    private Byte isSuper;

    private Integer priority;

    private String permsStr;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getPermsStr() {
        return permsStr;
    }

    public void setPermsStr(String permsStr) {
        this.permsStr = permsStr;
    }
}
