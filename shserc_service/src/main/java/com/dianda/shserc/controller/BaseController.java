package com.dianda.shserc.controller;

import com.alibaba.fastjson.JSONObject;
import com.dianda.common.common.Constant;
import com.dianda.shserc.entity.ResUser;
import com.dianda.common.exceptions.GlobalException;
import com.dianda.common.util.basic.ObjectUtil;
import com.dianda.common.security.shiro.util.ShiroUtil;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 控制器父类
 *
 * @author huachao
 * @since 2020-08-08
 */
@RestController("ShsercBaseController")
public class BaseController {

	public ResUser getLoginUserInfo() {
		JSONObject user = ShiroUtil.getLoginUser();
		if(!ObjectUtil.isNull(user)){
			ResUser resUser=new ResUser();
			resUser.setId(user.getLong("id"));
			resUser.setUserName(user.getString("userName"));
			resUser.setRealName (user.getString("realName"));
			resUser.setIdCard (user.getString("idCard"));
			resUser.setRoleId ( user.getInteger ( "roleId" ) );
			resUser.setCompanyName ( user.getString("companyName") );
			resUser.setCompanyId ( user.getInteger ("companyId") );
			resUser.setMobile ( user.getString ( "mobile" ) );
			resUser.setEmail ( user.getString ( "email" ) );
			resUser.setPhotoPath ( user.getString("photoPath") );
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
