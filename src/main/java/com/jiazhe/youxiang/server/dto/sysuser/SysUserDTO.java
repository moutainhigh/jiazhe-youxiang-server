package com.jiazhe.youxiang.server.dto.sysuser;

import com.jiazhe.youxiang.server.vo.BaseObject;
import java.util.Date;

/**
 * @author tu
 * @description：
 * @date 2018/10/19
 */
public class SysUserDTO extends BaseObject {

    private static final long serialVersionUID = 3868043216372041696L;

    /**
     * 员工id
     */
    private Integer id;

    /**
     * 员工电话
     */
    private String mobile;

    /**
     * 员工登录名
     */
    private String loginName;

    /**
     * 员工显示名
     */
    private String displayName;

    /**
     * 员工最后一次登录时间
     */
    private Date lastLoginTime;

    /**
     * 员工密码加密的盐值
     */
    private String salt;

    /**
     * 员工密码
     */
    private String password;

    /**
     * 员工最后一次登录IP
     */
    private String lastLoginIp;

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

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }
}
