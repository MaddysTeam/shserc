package com.dianda.shserc.util.shiro.jwt;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class JwtLogoutFilter extends LogoutFilter {
	
	@Override
	protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
		Subject subject = getSubject(request, response);
		try {
			subject.logout();
		} catch ( Exception e) {
		
		} finally {
			return false;
		}
	}
}
