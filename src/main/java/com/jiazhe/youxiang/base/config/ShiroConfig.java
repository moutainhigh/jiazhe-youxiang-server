package com.jiazhe.youxiang.base.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.jiazhe.youxiang.base.realm.CredentialsMatcher;
import com.jiazhe.youxiang.base.realm.CustomSessionDAO;
import com.jiazhe.youxiang.base.realm.CustomerRealm;
import com.jiazhe.youxiang.base.realm.UserModularRealmAuthenticator;
import com.jiazhe.youxiang.base.realm.UserRealm;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;


/**
 * Created by TU on 2018/8/27.
 */
@Configuration
public class ShiroConfig {

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager manager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(manager);
        // 未授权界面;
        bean.setUnauthorizedUrl("/system/index");
        //自定义拦截器
//        Map<String, Filter> filters = bean.getFilters();
//        filters.put("shiroLoginFilter", new ShiroLoginFilter());
//        bean.setFilters(filters);
        bean.setLoginUrl("/system/index");
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/api/preview/img/**", "anon"); //预览图片匿名访问
        filterChainDefinitionMap.put("/system/index", "anon"); //登录页url匿名访问
        filterChainDefinitionMap.put("/system/login", "anon");//登陆系统匿名访问
        filterChainDefinitionMap.put("/system/logout", "anon");//退出系统匿名访问
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/templates/**", "anon");
        filterChainDefinitionMap.put("/api/**", "perms");//接口通过权限认证
        filterChainDefinitionMap.put("/api/signin/**", "anon");//登录、发送验证码等匿名访问
        filterChainDefinitionMap.put("api/product/**", "anon");//放开商品验证

        //swagger相关连接可以直接访问
        filterChainDefinitionMap.put("/swagger-ui.html", "anon");
        filterChainDefinitionMap.put("/swagger-resources/**", "anon");
        filterChainDefinitionMap.put("/v2/api-docs/**", "anon");
        filterChainDefinitionMap.put("/webjars/**", "anon");
        //放开监控访问
        filterChainDefinitionMap.put("/actuator/**", "anon");

        filterChainDefinitionMap.put("/**", "authc");//表示所有url必须通过认证才能访问
        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return bean;
    }

    /**
     * 配置员工自定义的权限登录器
     */
    @Bean(name = "userRealm")
    public UserRealm userRealm() {
        UserRealm userRealm = new UserRealm();
        userRealm.setName("userRealm");
        userRealm.setCredentialsMatcher(credentialsMatcher());
        userRealm.setAuthorizationCacheName("shiro-authorizationCache");
        userRealm.setCacheManager(shiroEhCacheManager());
       /* userRealm.setCredentialsMatcher(hashedCredentialsMatcher());*/
        return userRealm;
    }

    /**
     * 配置客户自定义的权限登录器
     */
    @Bean(name = "customerRealm")
    public CustomerRealm customerRealm() {
        CustomerRealm customerRealm = new CustomerRealm();
        customerRealm.setName("customerRealm");
        customerRealm.setCredentialsMatcher(credentialsMatcher());
        customerRealm.setAuthorizationCacheName("shiro-authorizationCache");
        customerRealm.setCacheManager(shiroEhCacheManager());
        return customerRealm;
    }

    //配置自定义的密码比较器
    @Bean(name = "credentialsMatcher")
    public CredentialsMatcher credentialsMatcher() {
        CredentialsMatcher credentialsMatcher = new CredentialsMatcher();
        return credentialsMatcher;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }

    //    TODO niexiao  临时取消权限认证
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager());
        return advisor;
    }

    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

//    @Bean(name = "shiroLoginFilter")
//    public ShiroLoginFilter shiroLoginFilter() {
//        ShiroLoginFilter shiroLoginFilter = new ShiroLoginFilter();
//        return shiroLoginFilter;
//    }

    @Bean(name = "customSessionDAO")
    public CustomSessionDAO customSessionDao() {
        return new CustomSessionDAO();
    }

    @Bean
    public ModularRealmAuthenticator modularRealmAuthenticator() {
        //自己重写的ModularRealmAuthenticator
        UserModularRealmAuthenticator modularRealmAuthenticator = new UserModularRealmAuthenticator();
        modularRealmAuthenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
        return modularRealmAuthenticator;
    }

    @Bean(name = "securityManager")
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //设置realm.
        securityManager.setAuthenticator(modularRealmAuthenticator());
        List<Realm> realms = new ArrayList<>();
        //添加多个Realm
        realms.add(userRealm());
        realms.add(customerRealm());
        securityManager.setRealms(realms);
        securityManager.setCacheManager(shiroEhCacheManager());
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

    @Bean(name = "sessionManager")
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager defaultWebSessionManager = new DefaultWebSessionManager();
        defaultWebSessionManager.setGlobalSessionTimeout(1800000);
        defaultWebSessionManager.setDeleteInvalidSessions(true);
        defaultWebSessionManager.setSessionValidationSchedulerEnabled(true);
        defaultWebSessionManager.setSessionValidationInterval(1800000);
        defaultWebSessionManager.setSessionDAO(customSessionDao());
        return defaultWebSessionManager;
    }

    @Bean(name = "shiroEhCacheManager")
    public EhCacheManager shiroEhCacheManager() {
        EhCacheManager ehCacheManager = new EhCacheManager();
        return ehCacheManager;
    }

}
