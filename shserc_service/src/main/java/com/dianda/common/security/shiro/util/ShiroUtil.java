package com.dianda.common.security.shiro.util;

import com.alibaba.fastjson.JSONObject;
import com.dianda.common.security.shiro.jwt.JwtConstant;
import com.dianda.common.security.shiro.jwt.JwtOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

public class ShiroUtil {
	
	public static JSONObject  getLoginUser(){
		try {
			Subject subject = SecurityUtils.getSubject ( );
			String token = subject.getPrincipal ( ).toString ( );
			String account = JwtOperation.getClaim ( token , JwtConstant.ACCOUNT );
			JSONObject o = JSONObject.parseObject ( account );
			
			return o;
		}
		catch ( Exception e ){
			return null;
		}
	
	}
	
}
