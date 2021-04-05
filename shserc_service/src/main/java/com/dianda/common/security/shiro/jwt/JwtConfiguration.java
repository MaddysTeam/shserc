package com.dianda.common.security.shiro.jwt;

import com.dianda.common.security.shiro.service.IShrioCustomsizedService;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.util.ThreadContext;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
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
	
	public static JwtRoleFilter buildPermissionFilter(){
		return new JwtRoleFilter ();
	}
	
	public static JwtLogoutFilter buildLogoutFilter(){
		return new JwtLogoutFilter();
	}
	
	public static JwtCustomizeRealm buildRealm(){
		return new JwtCustomizeRealm();
	}
	
	public  static DefaultWebSecurityManager securityManager(JwtCustomizeRealm jwtRealm, CacheManager cacheManager){
		DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
		
		ThreadContext.bind(securityManager);
		
		// forbidden original  session
//		DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
//		DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
//		defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
//		subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
//		securityManager.setSubjectDAO(subjectDAO);
		
		// set cache manager
		securityManager.setCacheManager(cacheManager);
		
		// set realm
		securityManager.setRealm(jwtRealm);
		
		return securityManager;
	}
	

	public static ShiroFilterFactoryBean buildFilterFactory(DefaultWebSecurityManager securityManager,Map<String, String> filterRuleMap) {
		ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
		shiroFilter.setSecurityManager(securityManager);
		
		// setting filter
		Map<String, Filter> filterMap = new HashMap<> ();
		filterMap.put("jwt", buildJwtFilter());
		filterMap.put("role",buildPermissionFilter() );
		//filterMap.put("logout",buildLogoutFilter());
		
		shiroFilter.setFilters(filterMap);
		
		shiroFilter.setFilterChainDefinitionMap(filterRuleMap);
		
		return shiroFilter;
	}
	
}
