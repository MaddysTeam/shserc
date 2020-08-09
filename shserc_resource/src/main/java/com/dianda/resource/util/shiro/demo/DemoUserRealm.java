package com.dianda.resource.util.shiro.demo;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 *  @title UserRealm
 *  @Description  add  authorization and authentication here
 *  @author hauchao
 *  @Date 2020/2/23 22:27
 *  @Copyright 2019-2020 
 */

public class DemoUserRealm extends AuthorizingRealm {
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.addRole("admin");
		authorizationInfo.addRole("visitor");
		//authorizationInfo.addStringPermission();
		
		return authorizationInfo;
	}
	
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		String principal = ( String ) authenticationToken.getPrincipal();
		Object credentials = authenticationToken.getCredentials();
		if (null == principal) {
			return null;
		}
		
		return new SimpleAuthenticationInfo(principal, credentials, getName());
	}
	
}
