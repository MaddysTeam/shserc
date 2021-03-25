package com.dianda.shserc.service;

import com.dianda.shserc.dto.ChangePasswordDto;
import com.dianda.shserc.dto.ForgetPasswordDto;
import com.dianda.shserc.dto.LoginDto;

import javax.validation.Valid;


public interface IAccountService {

	LoginDto login(@Valid LoginDto login );
	
	boolean logout( );
	
	ChangePasswordDto changePassword(@Valid ChangePasswordDto dto );
	
	ForgetPasswordDto forgetPassword( ForgetPasswordDto dto );

}
