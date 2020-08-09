package com.dianda.resource.util.shiro.jwt;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *  @title JwtConfiguration
 *  @Description 描述
 *  @author huachao
 *  @Date 2020/3/8 17:34
 *  @Copyright 2019-2020
 */


public class JwtConfiguration {
	
	public static JwtFilter buildJwtFilter(){
			return new JwtFilter();
	}
	
	public static JwtLogoutFilter buildLogoutFilter(){
		return new JwtLogoutFilter();
	}
	
	public static JwtCustomizeRealm buildRealm(){
		return new JwtCustomizeRealm();
	}
	
	public  static DefaultWebSecurityManager securityManager(JwtCustomizeRealm jwtRealm, CacheManager cacheManager){
		DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
		
		// forbidden original  session
		DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
		DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
		defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
		subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
		securityManager.setSubjectDAO(subjectDAO);
		
		// set cache manager
		securityManager.setCacheManager(cacheManager);
		
		// set realm
		securityManager.setRealm(jwtRealm);
		
		return securityManager;
	}
	

	public static ShiroFilterFactoryBean buildFilterFactory(DefaultWebSecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
		shiroFilter.setSecurityManager(securityManager);
		
		// setting filter
		Map<String, Filter> filterMap = new HashMap<> ();
		filterMap.put("jwt", buildJwtFilter());
		filterMap.put("logout",buildLogoutFilter());
		shiroFilter.setFilters(filterMap);
		
		// setting filter chain map
		Map<String, String> filterRuleMap = new LinkedHashMap<> ();
		filterRuleMap.put("/account/login", "anon");
		filterRuleMap.put("/account/remote/login","anon");
		filterRuleMap.put("/user","anon");
		filterRuleMap.put("/logout","logout");
		filterRuleMap.put("/**", "jwt");
		shiroFilter.setFilterChainDefinitionMap(filterRuleMap);
		
		return shiroFilter;
	}
	
}
