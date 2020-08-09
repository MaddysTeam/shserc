package com.dianda.auth.util.shiro.jwt;

import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.dianda.auth.util.basic.ObjectUtil;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class JwtFilter extends BasicHttpAuthenticationFilter {
	
	//@Autowired
	//ShiroCache cache;
	
	@Override
	protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String auth = httpRequest.getHeader(JwtConstant.Authorization);
		
		return !ObjectUtil.isNull(auth);
	}
	
	@Override
	protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String token = httpRequest.getHeader(JwtConstant.Authorization);
		JwtToken jwtToken = new JwtToken(token);
		getSubject(request, response).login(jwtToken);
		
		return true;
	}
	
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		if (isLoginAttempt(request, response)) {
			try {
				Boolean result = executeLogin(request, response);
				return result;
			} catch (SignatureVerificationException e) {
			
			} catch (TokenExpiredException e) {
			
			} catch ( Exception e) {
				e.printStackTrace();
			} finally {
			
			}
		}
		
		return false;
	}
	
}
