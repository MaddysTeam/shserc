package com.dianda.shserc.controller;

import com.alibaba.fastjson.JSONObject;
import com.dianda.shserc.common.Constant;
import com.dianda.shserc.entity.ResUser;
import com.dianda.shserc.exceptions.GlobalException;
import com.dianda.shserc.util.basic.ObjectUtil;
import com.dianda.shserc.util.json.JsonResult;
import com.dianda.shserc.util.shiro.util.ShiroUtil;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.Subject;
import java.util.List;

/**
 * 用户控制器父类
 *
 * @author huachao
 * @since 2020-08-08
 */
@RestController
public class BaseController {

	public ResUser getLoginUserInfo() {
		JSONObject user = ShiroUtil.getLoginUser();
		if(!ObjectUtil.isNull(user)){
			ResUser resUser=new ResUser();
			resUser.setId(user.getInteger("id"));
			resUser.setUserName(user.getString("userName"));
			resUser.setRoleId ( user.getInteger ( "roleId" ) );
			return resUser;
		}
		
		throw new GlobalException ( Constant.ErrorCode.LOGIC_RESULT_INVALID,Constant.Error.LOGIN_USER_INVALID );
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
