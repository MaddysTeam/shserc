package com.dianda.auth.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dianda.auth.entity.ResUser;
import com.dianda.auth.util.shiro.jwt.JwtConstant;
import com.dianda.auth.util.shiro.jwt.JwtOperation;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {

	public ResUser getUserInfo() {
		String token = (String) SecurityUtils.getSubject().getPrincipal();
		String account = JwtOperation.getClaim(token, JwtConstant.ACCOUNT);
		JSONObject dto = JSON.parseObject(account);
		ResUser user= new ResUser();
		user.setId(dto.getInteger("id"));
		user.setUserName(dto.getString("userName"));

		return user;
	}


}
