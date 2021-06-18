package com.dianda.shsedu.controller;

import com.alibaba.fastjson.JSONObject;
import com.dianda.common.security.shiro.util.ShiroUtil;
import com.dianda.common.util.basic.ObjectUtil;
import com.dianda.shsedu.entity.ShseduUser;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * controller base
 *
 * @author huachao
 * @since 2020-08-08
 */
@RestController
public class BaseController {

	public ShseduUser getLoginUserInfo() {
		JSONObject user = ShiroUtil.getLoginUser();
		if (!ObjectUtil.isNull(user)) {
			ShseduUser shseduUser = new ShseduUser();
			shseduUser.setId(user.getInteger("id"));
			shseduUser.setUserName(user.getString("userName"));
			shseduUser.setRealName(user.getString("realName"));
			shseduUser.setRoleId(user.getInteger("roleId"));
			shseduUser.setMobile(user.getString("mobile"));
			shseduUser.setEmail(user.getString("email"));
			//resUser.setPhotoPath(user.getString("photoPath"));
			return shseduUser;
		}
		return null;
	}

	public String generateErrorMessage(BindingResult result){
		StringBuilder builder=new StringBuilder();
		List<ObjectError> errors= result.getAllErrors();
		for(ObjectError error : errors){
			builder.append(error.getDefaultMessage());
			builder.append ( "\r\n" );
		}

		return builder.toString();
	}

}
