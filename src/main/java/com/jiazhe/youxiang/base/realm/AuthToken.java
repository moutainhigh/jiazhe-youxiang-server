package com.jiazhe.youxiang.base.realm;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @author TU
 * @description 自定义带用户类型token
 * @date 2018/11/5.
 */
public class AuthToken extends UsernamePasswordToken {

    /**
	 * 用户类型
	 * 1:积分后台员工（User表）
	 * 2:积分兑换端用户（Customer表）
	 */
    private String userType;

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public AuthToken(String username, String password, String userType) {
        super(username, password);
        this.userType = userType;
    }

}
