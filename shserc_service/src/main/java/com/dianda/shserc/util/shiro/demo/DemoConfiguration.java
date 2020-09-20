package com.dianda.shserc.util.shiro.demo;

import com.dianda.shserc.util.shiro.session.CustomizeSessionListener;
import org.apache.commons.collections.map.LinkedMap;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class DemoConfiguration {
	
	
	private static final String SESSION_KEY = "shiro:session:";
	private static final int SESSION_EXPIRE_SECONDS = 12000;
	
	public static ShiroFilterFactoryBean buildShiroFilter(DefaultWebSecurityManager manager) {
		Map<String, String> map = new LinkedMap();
		map.put("/account/login", "anon");
		
		map.put("/swagger*","anon");
		map.put("/swagger-ui.html", "anon");
		map.put("/swagger-resources/**", "anon");
		map.put("/v2/**", "anon");
		map.put("/webjars/**", "anon");
		
		map.put("/account/logout", "authc");
		map.put("/user", "authc,roles[admin,visitor]");
		map.put("/**", "authc");
		
		ShiroFilterFactoryBean filterBean = new ShiroFilterFactoryBean();
		filterBean.setSecurityManager(manager);
		filterBean.setFilterChainDefinitionMap(map);
		filterBean.setLoginUrl("/account/login");
		
		return filterBean;
	}

	public static DemoUserRealm buildUserRealm() {
		return new DemoUserRealm();
	}
	
	/**
	 * @Description build security manager for shiro
	 */

	public static DefaultWebSecurityManager buildSecurityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		
		// set realm
		securityManager.setRealm(buildUserRealm());
		
		// set session manager
		securityManager.setSessionManager(buildSessionManager());
		
		// set security manager,otherwise the cas authentication action will throw no security manager exception
		//SecurityUtils.setSecurityManager(securityManager);
		
		//TODO:securityManager.setCacheManager(buildRedisCacheManager());
		
		return securityManager;
	}
	
	/**
	 * @Description build instance for shiro session manager
	 */
	
	public static SessionManager buildSessionManager() {
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		Collection<SessionListener> listeners = new ArrayList<SessionListener> ();
		listeners.add(buildSessionListener());
		sessionManager.setSessionListeners(listeners);
		sessionManager.setSessionIdCookie(buildSessionCookie());
		sessionManager.setSessionDAO(buildSessionDAO());
		//TODO:sessionManager.setCacheManager(buildRedisCacheManager());
		
		return sessionManager;
	}
	
	/**
	 * @Description build instance for redis cache manger and do following steps
	 * 1 : set host
	 * 2 : set timeout
	 */
	public static RedisManager buildRedisCacheManager() {
		RedisManager redisManager = new RedisManager();
		redisManager.setHost("192.168.1.9:6379"); //TODO: will put into  redis constant file
		redisManager.setTimeout(12000);  //TODO: will put into  redis constant file
		
		return redisManager;
	}
	
	
	/**
	 * @Description build customize session listener instance
	 */
	public static CustomizeSessionListener buildSessionListener() {
		return new CustomizeSessionListener();
	}
	
	/**
	 * @Description build customize session id generator instance
	 */
	public static SessionIdGenerator buildSessionIdGenerator() {
		return new JavaUuidSessionIdGenerator();
	}
	
	
	/**
	 * @Description build instance for enterprise cache session DAO
	 */
	public static SessionDAO buildSessionDAO() {
		RedisSessionDAO sessionDAO = new RedisSessionDAO();
		sessionDAO.setRedisManager(buildRedisCacheManager());
		sessionDAO.setKeyPrefix(SESSION_KEY);
		sessionDAO.setExpire(SESSION_EXPIRE_SECONDS);
		sessionDAO.setSessionIdGenerator(buildSessionIdGenerator());
		
		return sessionDAO;
	}
	
	
	/**
	 * @Description build instance for shiro simple cookie
	 * set http only to avoid  xss attack
	 * Ensure cookie disabled after browser been closed
	 */
	public static SimpleCookie buildSessionCookie() {
		SimpleCookie simpleCookie = new SimpleCookie("cookieId");
		simpleCookie.setHttpOnly(true);
		simpleCookie.setPath("/");
		simpleCookie.setMaxAge(999);
		
		return simpleCookie;
	}
	
	
}
