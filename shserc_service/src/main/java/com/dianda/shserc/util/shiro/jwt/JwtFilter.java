package com.dianda.shserc.util.shiro.jwt;

import com.alibaba.fastjson.JSONObject;
import com.dianda.shserc.util.SpringUtils;
import com.dianda.shserc.util.basic.ObjectUtil;
import com.dianda.shserc.util.basic.StringUtil;
import com.dianda.shserc.util.nosql.redis.RedisUtil;
import com.dianda.shserc.util.shiro.util.ShiroUtil;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;


import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;


public class JwtFilter extends BasicHttpAuthenticationFilter {
	
	@Resource
	RedisUtil redisUtil;
	
	@Override
	protected boolean isLoginAttempt( ServletRequest request , ServletResponse response ) {
		HttpServletRequest httpRequest = ( HttpServletRequest ) request;
		String auth = httpRequest.getHeader ( JwtConstant.Authorization );
		
		return ! ObjectUtil.isNull ( auth );
	}
	
//	@Override
//	protected boolean executeLogin( ServletRequest request , ServletResponse response ) throws Exception {
//		HttpServletRequest httpRequest = ( HttpServletRequest ) request;
//		String token = httpRequest.getHeader ( JwtConstant.Authorization );
//		JwtToken jwtToken = new JwtToken ( token );
//		try {
//			getSubject ( request , response ).login ( jwtToken );
//		} catch ( AuthenticationException e ) {
//			HttpUtil.ResponseUnAuthorized ( request , response , "login fail" );
//			return false;
//		}
//
//		return true;
//		return false;
//	}
	
	@Override
	protected boolean isAccessAllowed( ServletRequest request , ServletResponse response , Object mappedValue ) {
		Subject subject = getSubject ( request , response );
		if ( subject.isAuthenticated ( ) ) {
			JSONObject user= ShiroUtil.getLoginUser ();
			redisUtil=getRedisClient ();
			String token=redisUtil.get ( JwtConstant.CACHE_PREFIX+user.get ( "id" ));
			
			return !StringUtil.IsNullOrEmpty( token);
		}
//		else if ( isLoginAttempt ( request , response ) ) {
//			try {
//				Boolean result = executeLogin ( request , response );
//				return result;
//			} catch ( SignatureVerificationException e ) {
//				e.printStackTrace ( );
//			} catch ( TokenExpiredException e ) {
//				e.printStackTrace ( );
//			} catch ( Exception e ) {
//				e.printStackTrace ( );
//			} finally {
//
//			}
//		}
		
		return false;
	}
	
	
	protected boolean refreshToken( ) {
		return true;
	}
	
	
	private RedisUtil getRedisClient( ) {
		return ObjectUtil.isNull ( redisUtil ) ? SpringUtils.getBean ( RedisUtil.class ) : redisUtil;
	}
	
}
