package com.dianda.auth.controller;


import com.dianda.auth.dto.LoginDto;
import com.dianda.auth.service.IAccountService;
import com.dianda.auth.util.basic.ObjectUtil;
import com.dianda.auth.util.json.JsonResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * Account 控制器
 * </p>
 *
 * @author huachao
 * @since 2020-08-05
 */
@RestController
@RequestMapping( "/account" )
public class AccountController {
	
	@Autowired
	IAccountService accountService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public JsonResult Login( @RequestBody LoginDto loginDto) {
		if ( ObjectUtil.isNull(loginDto))
			return JsonResult.error();
		
		loginDto =accountService.Login ( loginDto );
		
		return  JsonResult.success ( loginDto,"success" );
	}
	

}

