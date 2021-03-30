package com.dianda.shserc.controller;


import com.dianda.shserc.common.Constant;
import com.dianda.shserc.dto.ChangePasswordDto;
import com.dianda.shserc.dto.LoginDto;
import com.dianda.shserc.entity.ResUser;
import com.dianda.shserc.service.IAccountService;
import com.dianda.shserc.service.IResRoleService;
import com.dianda.shserc.util.crypto.CryptoSecret;
import com.dianda.shserc.util.json.JsonResult;
import com.dianda.shserc.vo.ResUserVo;
import com.dianda.shserc.vo.mappers.IUserVoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Account controller
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
			return JsonResult.error ( "validate error" );
		}
		
		//de-encrypt password
		String password = CryptoSecret.desEncrypt ( loginDto.getPassword ( ) );
		loginDto.setPassword ( password );
		
		//try to login
		boolean result = accountService.login ( loginDto );
		if ( result ) {
			// get login user info
			ResUserVo resUserVo = IUserVoMapper.INSTANCE.mapFrom ( super.getLoginUserInfo ( ) );
			//set token to user vo and transfer to frontend
			resUserVo.setToken ( loginDto.getToken ( ) );
			
			return JsonResult.success ( resUserVo , "success" );
		} else {
			return JsonResult.error ( loginDto.getMessage ( ) );
		}
	}
	
	@RequestMapping( value = "/logout", method = RequestMethod.POST )
	//@RequiresRoles( "admin" )
	public JsonResult logout( ) {
		return accountService.logout ( ) ? JsonResult.success ( ) : JsonResult.error ( );
	}
	
	@RequestMapping( value = "/password/change", method = RequestMethod.POST )
	public JsonResult changePassword( @RequestBody @Valid ChangePasswordDto changePasswordDto , BindingResult bindingResult ) {
		if ( bindingResult.hasErrors ( ) ) {
			return JsonResult.error ( super.generateErrorMessage ( bindingResult ) );
		}
		
		ResUser user = super.getLoginUserInfo ( );
		
		changePasswordDto.setUserName ( user.getUserName ( ) );
		boolean result = accountService.changePassword ( changePasswordDto );
		return result ? JsonResult.success ( changePasswordDto , Constant.Success.EDIT_SUCCESS ) :
				JsonResult.error ( Constant.Error.EDIT_FAILURE );
		
	}
	
}

