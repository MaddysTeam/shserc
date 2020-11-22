package com.dianda.shserc.util.shiro.util;

import com.alibaba.fastjson.JSONObject;
import com.dianda.shserc.util.shiro.jwt.JwtConstant;
import com.dianda.shserc.util.shiro.jwt.JwtOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

public class ShiroUtil {
	
	public static JSONObject  getLoginUser(){
		Subject subject= SecurityUtils.getSubject ();
		String token = subject.getPrincipal ( ).toString ( );
		String account = JwtOperation.getClaim ( token , JwtConstant.ACCOUNT );
		JSONObject o = JSONObject.parseObject ( account );
		
		return o;
	}
	
}
