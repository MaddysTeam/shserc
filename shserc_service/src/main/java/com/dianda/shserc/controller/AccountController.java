package com.dianda.shserc.controller;


import com.dianda.shserc.dto.ChangePasswordDto;
import com.dianda.shserc.dto.LoginDto;
import com.dianda.shserc.service.IAccountService;
import com.dianda.shserc.util.json.JsonResult;
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
		if ( bindingResult.hasErrors ( ) ) {
			return JsonResult.error ("validate error");
		}
		
		loginDto = accountService.login ( loginDto );
		if ( loginDto.getIsSuccess ( ) ) {
			return JsonResult.success ( loginDto , "success" );
		} else {
			return JsonResult.error ( loginDto.getMessage ( ) );
		}
	}
	
	@RequestMapping( value = "/logout", method = RequestMethod.POST )
	@RequiresRoles( "admin" )
	public JsonResult logout( ) {
		boolean result = accountService.logout ( );
		if ( result )
			return JsonResult.success ( );
		else
			return JsonResult.error ( );
	}
	
	public JsonResult ChangePassword( @RequestBody @Valid ChangePasswordDto changePasswordDto , BindingResult bindingResult ) {
		if ( bindingResult.hasErrors ( ) ) {
			return JsonResult.error ( );
		}
		
		changePasswordDto = accountService.changePassword ( changePasswordDto );
		if ( changePasswordDto.getIsSuccess ( ) ) {
			return JsonResult.success ( changePasswordDto , "success" );
		} else {
			return JsonResult.error ( changePasswordDto.getMessage ( ) );
		}
	}
	
	
}

