package com.jiazhe.youxiang.server.dto.sysuser;

import com.jiazhe.youxiang.server.vo.BaseObject;

/**
 * @author tu
 * @description：
 * @date 2018/10/19
 */
public class UserWithRoleDTO extends BaseObject {

    private static final long serialVersionUID = -2506514569930577071L;

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
     * 员工密码
     */
    private String password;

    /**
     * 员工密码盐值
     */
    private String salt;

    /**
     * 角色id，多个id用英文逗号连接
     */
    private String roleIds;

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

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
