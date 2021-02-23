package com.dianda.shserc.util.basic;

import com.alibaba.fastjson.JSON;
import com.dianda.shserc.util.json.JsonResult;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class HttpUtil {
	
	
	public static void ResponseUnAuthorized(ServletRequest request, ServletResponse response, String msg) throws IOException {
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		httpResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
		httpResponse.setCharacterEncoding("UTF-8");
		httpResponse.setContentType("application/json,charset=utf-8");
		
		JsonResult result = JsonResult.unAuthorized(msg);
		PrintWriter writer = null;
		try {
			writer = httpResponse.getWriter();
			writer.append( JSON.toJSONString(result));
		} catch ( IOException e) {
		
		} finally {
			if (!ObjectUtil.isNull(writer))
				writer.close();
		}
		
	}

	public static String getRequestIP(HttpServletRequest request) throws UnknownHostException {
		if(ObjectUtil.isNull(request))
			return InetAddress.getLocalHost().getHostAddress();
		String ip = request.getHeader("x-forwarded-for");
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}



}
