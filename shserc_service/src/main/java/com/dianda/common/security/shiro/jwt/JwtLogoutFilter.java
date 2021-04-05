package com.dianda.common.security.shiro.jwt;

import com.dianda.common.exceptions.GlobalException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class JwtLogoutFilter extends LogoutFilter {
	
	@Override
	protected boolean preHandle(ServletRequest request, ServletResponse response) throws GlobalException {
		Subject subject = getSubject(request, response);
		try {
			subject.logout();
		} catch ( Exception e) {
		
		} finally {
			return false;
		}
	}
}
