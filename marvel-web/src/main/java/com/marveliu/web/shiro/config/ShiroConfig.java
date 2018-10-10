package com.marveliu.web.shiro.config;

import com.marveliu.web.shiro.realm.AModularRealmAuthenticator;
import com.marveliu.web.shiro.realm.RealmManager;
import com.marveliu.web.shiro.filter.ASubjectFactory;
import com.marveliu.web.shiro.filter.ShiroFilterChainManager;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Marveliu
 * @Date: 2018/9/29 下午2:45
 * @Description:
 **/

@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager, ShiroFilterChainManager filterChainManager) {

        RestShiroFilterFactoryBean shiroFilterFactoryBean = new RestShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // shiroFilter配置
        shiroFilterFactoryBean.setFilters(filterChainManager.initGetFilters());
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainManager.initGetFilterChain());
        return shiroFilterFactoryBean;
    }

    @Bean
    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public SecurityManager securityManager(RealmManager realmManager) {

        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setAuthenticator(new AModularRealmAuthenticator());

        DefaultSubjectDAO subjectDAO = (DefaultSubjectDAO) securityManager.getSubjectDAO();
        DefaultSessionStorageEvaluator evaluator = (DefaultSessionStorageEvaluator) subjectDAO.getSessionStorageEvaluator();

        ASubjectFactory subjectFactory = new ASubjectFactory(evaluator);
        securityManager.setSubjectFactory(subjectFactory);
        securityManager.setRealms(realmManager.initGetRealm());
        SecurityUtils.setSecurityManager(securityManager);
        return securityManager;
    }
}
