package com.dianda.auth.controller;


import com.dianda.auth.dto.LoginDto;
import com.dianda.auth.service.IAccountService;
import com.dianda.auth.util.basic.ObjectUtil;
import com.dianda.auth.util.json.JsonResult;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
public class AccountController extends BaseController {
	
	@Autowired
	IAccountService accountService;
	
	@RequestMapping( value = "/login", method = RequestMethod.POST )
	public JsonResult login( @RequestBody @Valid @Validated LoginDto loginDto , BindingResult bindingResult ) {
		if(bindingResult.hasErrors ()){
			return JsonResult.error ();
		}
		
		loginDto = accountService.login ( loginDto );
		if ( loginDto.getIsSuccess ( ) ) {
			return JsonResult.success ( loginDto , "success" );
		} else {
			return JsonResult.error ( loginDto.getMessage ( ) );
		}
	}
	
	@RequestMapping( value = "/logout", method = RequestMethod.POST )
	@RequiresRoles( "Admin" )
	public JsonResult logout( ) {
		boolean result = accountService.logout ( );
		if ( result )
			return JsonResult.success ( );
		else
			return JsonResult.error ( );
	}
	
	
}

