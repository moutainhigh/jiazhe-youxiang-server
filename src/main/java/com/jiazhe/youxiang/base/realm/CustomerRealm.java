package com.jiazhe.youxiang.base.realm;

import com.jiazhe.youxiang.server.biz.CustomerBiz;
import com.jiazhe.youxiang.server.dto.customer.CustomerDTO;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author TU
 * @description
 * @date 2018/11/5.
 */
public class CustomerRealm extends AuthorizingRealm {

    @Autowired
    private CustomerBiz customerBiz;

    private static final Logger logger = LoggerFactory.getLogger(CustomerRealm.class);

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        logger.info("=========进入客户登录验证============");
        try {
            AuthToken utoken = (AuthToken) token;
            String mobile = utoken.getUsername();
            CustomerDTO customerDTO = customerBiz.getByMobile(mobile);
            if (null != customerDTO) {
                return new SimpleAuthenticationInfo(customerDTO, null, null, this.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }
}
