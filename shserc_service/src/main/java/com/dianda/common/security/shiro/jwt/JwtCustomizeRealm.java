package com.dianda.common.security.shiro.jwt;

import com.dianda.common.security.shiro.service.IShrioCustomsizedService;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;

public class JwtCustomizeRealm extends AuthorizingRealm {
	
	@Resource
	IShrioCustomsizedService shrioCustomsizedService;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo( PrincipalCollection principalCollection ) {
		return shrioCustomsizedService.authorize ( principalCollection );
	}
	
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo( AuthenticationToken authenticationToken ){
		return shrioCustomsizedService.authenticate ( authenticationToken );
	}

	@Override
	public boolean supports( AuthenticationToken token ) {
		return token instanceof JwtToken;
	}
}
