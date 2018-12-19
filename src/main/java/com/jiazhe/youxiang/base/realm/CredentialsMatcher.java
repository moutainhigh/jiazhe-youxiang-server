package com.jiazhe.youxiang.base.realm;

import com.jiazhe.youxiang.server.common.enums.LoginType;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SaltedAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.crypto.hash.SimpleHash;

/**
 *自定义密码比较器类
 */
public class CredentialsMatcher extends SimpleCredentialsMatcher {

    /**
     *校验
     */
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        AuthToken utoken=(AuthToken) token;
        //获得用户输入的密码:(可以采用加盐(salt)的方式去检验)
        if(utoken.getUserType().equals(LoginType.USER.toString())){
            String inPassword = new String(utoken.getPassword());
            Object salt = ((SaltedAuthenticationInfo) info).getCredentialsSalt();
            String saltPassword = new SimpleHash("MD5",inPassword,salt,1024).toString();
            //获得数据库中的密码
            String dbPassword=(String) info.getCredentials();
            //进行密码的比对
            return this.equals(saltPassword, dbPassword);
        }else{
            return true;
        }

    }
}
