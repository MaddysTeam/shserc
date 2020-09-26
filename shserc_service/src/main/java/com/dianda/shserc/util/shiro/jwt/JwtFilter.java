package com.dianda.shserc.util.shiro.jwt;

import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.dianda.shserc.util.basic.HttpUtil;
import com.dianda.shserc.util.basic.ObjectUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class JwtFilter extends BasicHttpAuthenticationFilter {
	
	//@Autowired
	//ShiroCache cache;
	
	@Override
	protected boolean isLoginAttempt( ServletRequest request , ServletResponse response ) {
		HttpServletRequest httpRequest = ( HttpServletRequest ) request;
		String auth = httpRequest.getHeader ( JwtConstant.Authorization );
		
		return ! ObjectUtil.isNull ( auth );
	}
	
	@Override
	protected boolean executeLogin( ServletRequest request , ServletResponse response ) throws Exception {
		HttpServletRequest httpRequest = ( HttpServletRequest ) request;
		String token = httpRequest.getHeader ( JwtConstant.Authorization );
		JwtToken jwtToken = new JwtToken ( token );
		try {
			getSubject ( request , response ).login ( jwtToken );
		} catch ( AuthenticationException e ) {
			HttpUtil.ResponseUnAuthorized ( request , response , "login fail" );
			return false;
		}
		
		return true;
	}
	
	@Override
	protected boolean isAccessAllowed( ServletRequest request , ServletResponse response , Object mappedValue ) {
		Subject subject = getSubject ( request , response );
		if ( subject.isAuthenticated ( ) ) {
			//TODO:
			return true;
		} else if ( isLoginAttempt ( request , response ) ) {
			try {
				Boolean result = executeLogin ( request , response );
				return result;
			} catch ( SignatureVerificationException e ) {
				e.printStackTrace ( );
			} catch ( TokenExpiredException e ) {
				e.printStackTrace ( );
			} catch ( Exception e ) {
				e.printStackTrace ( );
			} finally {
			
			}
		}
		
		return false;
	}
	
}
