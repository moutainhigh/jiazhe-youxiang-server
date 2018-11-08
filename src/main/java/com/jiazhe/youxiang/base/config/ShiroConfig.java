package com.jiazhe.youxiang.base.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.jiazhe.youxiang.base.realm.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.eis.MemorySessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.shiro.codec.Base64;

import javax.servlet.Filter;
import java.util.*;


/**
 * Created by TU on 2018/8/27.
 */
@Configuration
public class ShiroConfig {

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager manager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(manager);
        //自定义拦截器
        Map<String, Filter> filters = bean.getFilters();
        filters.put("shiroLoginFilter", new ShiroLoginFilter());
        bean.setFilters(filters);
        /*bean.setLoginUrl("../system/index");*/
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/system/index", "anon"); //登录页url匿名访问
        filterChainDefinitionMap.put("/system/login", "anon");//登陆系统匿名访问
        filterChainDefinitionMap.put("/system/logout", "anon");//退出系统匿名访问
        filterChainDefinitionMap.put("/api/signin/usersendcode", "anon");//发送验证码匿名访问
        filterChainDefinitionMap.put("/api/signin/usersignin", "anon");//后台登陆请求
        filterChainDefinitionMap.put("/api/signin/customersendcode", "anon");//发送验证码匿名访问
        filterChainDefinitionMap.put("/api/signin/customersignin", "anon");//前台登陆请求
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/templates/**", "anon");
        filterChainDefinitionMap.put("/**", "anon");//表示所有url必须通过认证才能访问
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
        userRealm.setCacheManager(ehCacheManager());
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
        customerRealm.setCacheManager(ehCacheManager());
        return customerRealm;
    }

    //配置自定义的密码比较器
    @Bean(name = "credentialsMatcher")
    public CredentialsMatcher credentialsMatcher(){
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

    @Bean(name = "shiroLoginFilter")
    public ShiroLoginFilter shiroLoginFilter() {
        ShiroLoginFilter shiroLoginFilter = new ShiroLoginFilter();
        return shiroLoginFilter;
    }

    @Bean(name = "sessionDAO")
    public MemorySessionDAO memorySessionDAO() {
        return new MemorySessionDAO();
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
       /* securityManager.setRememberMeManager(rememberMeManager());*/
        securityManager.setCacheManager(ehCacheManager());
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

   /* @Bean(name = "rememberMe")
    public SimpleCookie rememberMeCookie() {
        //System.out.println("ShiroConfiguration.rememberMeCookie()");
        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //<!-- 记住我cookie生效时间30天 ,单位秒;-->
        simpleCookie.setMaxAge(259200);
        return simpleCookie;
    }*/

    /*@Bean(name = "rememberMeManager")
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
        byte[] cipherKey = Base64.decode("2AvVhdsgUs0FSA3SDFAdag==");
        cookieRememberMeManager.setCipherKey(cipherKey);
        return cookieRememberMeManager;
    }*/

    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        hashedCredentialsMatcher.setHashIterations(1024);
        return hashedCredentialsMatcher;
    }

    @Bean(name = "sessionManager")
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager defaultWebSessionManager = new DefaultWebSessionManager();
        defaultWebSessionManager.setGlobalSessionTimeout(1800000);
        defaultWebSessionManager.setDeleteInvalidSessions(true);
        defaultWebSessionManager.setSessionValidationSchedulerEnabled(true);
        defaultWebSessionManager.setSessionValidationInterval(1800000);
        defaultWebSessionManager.setSessionDAO(memorySessionDAO());
       /* defaultWebSessionManager.setSessionIdCookie(rememberMeCookie());*/
        return defaultWebSessionManager;
    }

    @Bean(name = "cacheManager")
    public EhCacheManager ehCacheManager() {
        EhCacheManager ehCacheManager = new EhCacheManager();
        return ehCacheManager;
    }

}
