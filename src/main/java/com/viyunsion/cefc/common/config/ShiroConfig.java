/*
 * Copyright (c) 2022 Astroline All rights reserved.
 *
 * @date: 8/12/22, 12:00 AM
 * @author: Astroline <Astroline_kamu@outlook.com>
 *
 * https://niyredra.com
 *
 * 在下鸭爪，全宇宙最凶狠的龙！
 * 嗷～
 */

package com.viyunsion.cefc.common.config;

import com.viyunsion.cefc.modules.sys.oauth2.OAuth2Filter;
import com.viyunsion.cefc.modules.sys.oauth2.OAuth2Realm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * ShiroConfig 注入Bean
 * @author Niyredra Astroline_kamu@outlook.com
 */
@Configuration
public class ShiroConfig {

    @Bean
    public SecurityManager securityManager(OAuth2Realm realm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        securityManager.setRememberMeManager(null);
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactory(SecurityManager manager){
        ShiroFilterFactoryBean shiroFilterFactory = new ShiroFilterFactoryBean();
        shiroFilterFactory.setSecurityManager(manager);

        // OAuth2
        Map<String, Filter> filterMap = new HashMap<>() {{
            // Filter Name
           put("OAuth2", new OAuth2Filter());
        }};
        shiroFilterFactory.setFilters(filterMap);

        Map<String, String> filterPath = new LinkedHashMap<>() {{
            // Filter Path
            put("/test.png", "anon");
            put("/sys/login", "anon");
            put("/sys/register", "anon");
//            put("/**", "OAuth2");
            put("/**", "anon");
        }};
        shiroFilterFactory.setFilterChainDefinitionMap(filterPath);

        return shiroFilterFactory;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager manager){
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(manager);
        return advisor;
    }
}
