package com.dianda.auth.util.shiro.jwt;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.io.UnsupportedEncodingException;

public class JwtCustomizeRealm extends AuthorizingRealm {
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		SimpleAuthorizationInfo  authorizationInfo=new SimpleAuthorizationInfo();
		Object principal=principalCollection.getPrimaryPrincipal();
		
		authorizationInfo.addRole("Admin");
		
		return authorizationInfo;
	}
	
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		String token = ( String ) authenticationToken.getPrincipal();
		Object credentials = authenticationToken.getCredentials();
		String account = JwtOperation.getClaim(token, JwtConstant.ACCOUNT);
		try {
			if (JwtOperation.verifyToken(token))
				return new SimpleAuthenticationInfo(token, credentials, account);
		} catch ( UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		throw new AuthenticationException("token invalid");
	}
	
	
	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof JwtToken;
	}
}
