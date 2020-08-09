package com.dianda.resource.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author huachao
 * @title CORSFilter
 * @Description 描述
 * @Date 2020/8/8 15:32
 */
public class CORSFilter implements Filter {
	
	
	@Override
	public void init( FilterConfig filterConfig ) throws ServletException {
	
	}
	
	@Override
	public void doFilter( ServletRequest servletRequest , ServletResponse servletResponse , FilterChain filterChain ) throws IOException, ServletException {
		HttpServletRequest req = ( HttpServletRequest ) servletRequest;
		HttpServletResponse res = (HttpServletResponse) servletResponse;
		
		res.setHeader("Access-Control-Allow-Credentials","true");
		res.setHeader("Access-Control-Allow-Origin", req.getHeader("Origin"));
		res.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		res.setHeader("Access-Control-Max-Age", "3600");
		res.setHeader("Access-Control-Allow-Headers", "x-requested-with");
	}
	
	@Override
	public void destroy( ) {
	
	}
}
