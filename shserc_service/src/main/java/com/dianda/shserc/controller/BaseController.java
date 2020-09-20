package com.dianda.shserc.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dianda.shserc.entity.ResUser;
import com.dianda.shserc.util.shiro.jwt.JwtConstant;
import com.dianda.shserc.util.shiro.jwt.JwtOperation;
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
