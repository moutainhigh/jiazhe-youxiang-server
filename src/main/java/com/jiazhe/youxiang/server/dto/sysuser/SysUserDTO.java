package com.jiazhe.youxiang.server.dto.sysuser;

import java.util.Date;

/**
 * @author tu
 * @description：
 * @date 2018/10/19
 */
public class SysUserDTO {

    private Integer id;

    private String mobile;

    private String name;

    private Date lastLoginTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
}
