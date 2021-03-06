package com.dianda.common.security.shiro;

import com.dianda.common.security.shiro.service.IShrioCustomsizedService;
import com.dianda.common.util.nosql.redis.RedisSettings;
import com.dianda.common.security.shiro.jwt.JwtConfiguration;
import com.dianda.common.security.shiro.jwt.JwtCustomizeRealm;
import com.dianda.common.security.shiro.jwt.JwtFilter;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.annotation.Resource;

/**
 * @author huachao
 * @title ShiroConfig
 * @Description 描述
 * @Date 2020/2/17 0:23
 * @Copyright 2019-2020
 */

@Configuration
public class ShiroConfig {
	
	@Resource
	RedisSettings redisSettings;
	
	//TODO:  this wil  be use by  multi   implements  , you can  determine which project name will be use or not
	@Resource()
	@Qualifier("ShseduShiroService")
	IShrioCustomsizedService shrioCustomsizedService;
	
	@Bean
	public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor( ) {
		return new LifecycleBeanPostProcessor ( );
	}
	
	@Bean
	@DependsOn( "lifecycleBeanPostProcessor" )
	public static DefaultAdvisorAutoProxyCreator getLifecycleBeanPostProcessor( ) {
		DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator ( );
		defaultAdvisorAutoProxyCreator.setProxyTargetClass ( true );
		return defaultAdvisorAutoProxyCreator;
	}
	
	/**
	 * @Description ensure invoke authorization logic in realm when using annotation before action
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor( DefaultWebSecurityManager securityManager ) {
		AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor ( );
		advisor.setSecurityManager ( securityManager );
		return advisor;
	}
	
	/**
	 * @Description build security manager for shiro
	 */
	@Bean("securityManager")
	public DefaultWebSecurityManager buildSecurityManager( JwtCustomizeRealm realm ) {
		return JwtConfiguration.securityManager ( realm , BuildCacheManager ( ) );
	}
	
	@Bean
	public JwtFilter buildJwtFilter( ) {
		return JwtConfiguration.buildJwtFilter ( );
	}
	
	@Bean
	public JwtCustomizeRealm buildJwtRealm( ) {
		return JwtConfiguration.buildRealm ( );
	}
	
	@Bean
	public ShiroFilterFactoryBean buildShiroFilter( DefaultWebSecurityManager manager ) {
		return JwtConfiguration.buildFilterFactory ( manager ,shrioCustomsizedService.setFilterRoleMap ());
	}
	
	
	/**
	 *  TODO:  jwt authenticate dont need origin session
	 *  @Description build instance for shiro session manager
	 */
//	@Bean
//	public SessionManager buildSessionManager() {
//		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
//		Collection<SessionListener> listeners = new ArrayList<SessionListener>();
//		listeners.add(buildSessionListener());
//		sessionManager.setSessionListeners(listeners);
//		sessionManager.setSessionIdCookie(buildSessionCookie());
//		sessionManager.setSessionDAO(buildSessionDAO());
//		sessionManager.setCacheManager(buildRedisCacheManager());
//
//		return sessionManager;
//	}
	
	/**
	 * @Description build instance for redis cache manger and do following steps
	 * 1 : set host
	 * 2 : set timeout
	 */
	@Bean
	public RedisManager buildRedisCacheManager( ) {
		RedisManager redisManager = new RedisManager ( );
		redisManager.setHost ( redisSettings.host + ":" + redisSettings.port );
		redisManager.setTimeout ( redisSettings.timeout );
		
		return redisManager;
	}
	
	@Bean
	public RedisCacheManager BuildCacheManager( ) {
		
		RedisCacheManager cacheManager = new RedisCacheManager ( );
		cacheManager.setRedisManager ( buildRedisCacheManager ( ) );
		cacheManager.setExpire ( 200000);
		
		return cacheManager;
	}

//
//
//	/**
//	 * @Description build customize session listener instance
//	 */
//	@Bean("sessionListener")
//	public CustomizeSessionListener buildSessionListener() {
//		return new CustomizeSessionListener();
//	}
//
//	/**
//	 * @Description build customize session id generator instance
//	 */
//	@Bean
//	public SessionIdGenerator buildSessionIdGenerator() {
//		return new JavaUuidSessionIdGenerator();
//	}
//
//
//	/**
//	 * @Description build instance for enterprise cache session DAO
//	 */
//	@Bean
//	public SessionDAO buildSessionDAO() {
//		RedisSessionDAO sessionDAO = new RedisSessionDAO();
//		sessionDAO.setRedisManager(buildRedisCacheManager());
//		sessionDAO.setKeyPrefix(SESSION_KEY);
//		sessionDAO.setExpire(SESSION_EXPIRE_SECONDS);
//		sessionDAO.setSessionIdGenerator(buildSessionIdGenerator());
//
//		return sessionDAO;
//	}
//
//
//	/**
//	 * @Description build instance for shiro simple cookie
//	 * set http only to avoid  xss attack
//	 * Ensure cookie disabled after browser been closed
//	 */
//	@Bean
//	public SimpleCookie buildSessionCookie() {
//		SimpleCookie simpleCookie = new SimpleCookie("cookieId");
//		simpleCookie.setHttpOnly(true);
//		simpleCookie.setPath("/");
//		simpleCookie.setMaxAge(999);
//
//		return simpleCookie;
//	}
//
//

}
