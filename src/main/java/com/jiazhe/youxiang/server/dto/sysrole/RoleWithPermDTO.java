package com.jiazhe.youxiang.server.dto.sysrole;

/**
 * Created by TU on 2018/10/18.
 */
public class RoleWithPermDTO {

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
