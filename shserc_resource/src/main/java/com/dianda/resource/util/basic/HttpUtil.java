package com.dianda.resource.util.basic;

import com.alibaba.fastjson.JSON;
import com.dianda.resource.util.json.JsonResult;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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
			writer.append(JSON.toJSONString(result));
		} catch ( IOException e) {
		
		} finally {
			if (!ObjectUtil.isNull(writer))
				writer.close();
		}
		
	}
	
	
}
