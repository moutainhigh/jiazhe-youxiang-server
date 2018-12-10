package com.jiazhe.youxiang.base.realm;

import com.jiazhe.youxiang.server.biz.CustomerBiz;
import com.jiazhe.youxiang.server.common.enums.LoginType;
import com.jiazhe.youxiang.server.dto.customer.CustomerDTO;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

/**
 * @author TU
 * @description
 * @date 2018/11/5.
 */
public class CustomerRealm extends AuthorizingRealm {

    @Autowired
    @Lazy
    private CustomerBiz customerBiz;

    private static final Logger logger = LoggerFactory.getLogger(CustomerRealm.class);

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        logger.info("=========进入客户登录验证============");
        try {
            AuthToken utoken = (AuthToken) token;
            if (utoken.getUserType().equals(LoginType.CUSTOMER.toString())) {
                String mobile = utoken.getUsername();
                CustomerDTO customerDTO = customerBiz.getByMobile(mobile);
                if (null != customerDTO) {
                    return new SimpleAuthenticationInfo(customerDTO, null, null, this.getName());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("=========进入客户授权============");
        if (principalCollection.getPrimaryPrincipal() instanceof CustomerDTO) {
            CustomerDTO customerDTO = (CustomerDTO) principalCollection.getPrimaryPrincipal();
            if (customerDTO != null) {
                SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
                info.addStringPermission("test:ajaxtest");
                //TODO niexiao 修改成客户可用的权限列表
                return info;
            }
        }
        return null;
    }
}
