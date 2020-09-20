package com.dianda.shserc.util.shiro.jwt;

import org.apache.shiro.authc.AuthenticationToken;


/**
 *  @title JwtToken
 *  @Description JwtToken 实体
 *  @author huachao
 *  @Date 2020/3/8 16:21
 *  @Copyright 2019-2020
 */

public class JwtToken  implements AuthenticationToken {
	
	private String token;
	
	public JwtToken( String token) {
		this.token = token;
	}
	
	@Override
	public Object getPrincipal() {
		return this.token;
	}
	
	@Override
	public Object getCredentials() {
		return this.token;
	}
	
}
