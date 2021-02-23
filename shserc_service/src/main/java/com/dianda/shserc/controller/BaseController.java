package com.dianda.shserc.controller;

import com.alibaba.fastjson.JSONObject;
import com.dianda.shserc.entity.ResUser;
import com.dianda.shserc.util.basic.ObjectUtil;
import com.dianda.shserc.util.shiro.util.ShiroUtil;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {

	public ResUser getUserInfo() {
		JSONObject user = ShiroUtil.getLoginUser();
		if(!ObjectUtil.isNull(user)){
			ResUser resUser=new ResUser();
			resUser.setId(user.getInteger("id"));
			resUser.setUserName(user.getString("userName"));
			return resUser;
		}

//		ResUser user = new ResUser ( );
//		String token = (String) SecurityUtils.getSubject().getPrincipal();
//		String account = JwtOperation.getClaim(token, JwtConstant.ACCOUNT);
//		if( null != account  ){
//			JSONObject dto = JSON.parseObject ( account );
//			user.setId ( dto.getInteger ( "id" ) );
//			user.setUserName ( dto.getString ( "userName" ) );
//		}
		
		return null;
	}


}
