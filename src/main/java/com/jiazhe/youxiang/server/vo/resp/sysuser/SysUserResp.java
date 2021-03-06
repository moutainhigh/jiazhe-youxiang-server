package com.jiazhe.youxiang.server.vo.resp.sysuser;

import com.jiazhe.youxiang.server.vo.BaseVO;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author tu
 * @description：
 * @date 2018/10/19
 */
public class SysUserResp extends BaseVO {

    @ApiModelProperty("员工id")
    private Integer id;

    @ApiModelProperty("员工电话")
    private String mobile;

    @ApiModelProperty("员工登陆名")
    private String loginName;

    @ApiModelProperty("员工显示名")
    private String displayName;

    @ApiModelProperty("最后登录时间")
    private Long lastLoginTime;

    @ApiModelProperty("最后登录IP")
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

    public Long getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Long lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }
}
