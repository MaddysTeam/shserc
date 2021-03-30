package com.dianda.shserc.service;

import com.dianda.shserc.dto.ChangePasswordDto;
import com.dianda.shserc.dto.ForgetPasswordDto;
import com.dianda.shserc.dto.LoginDto;

import javax.validation.Valid;


public interface IAccountService {

	boolean login(@Valid LoginDto login );
	
	boolean logout( );
	
	boolean changePassword(@Valid ChangePasswordDto dto );
	
	boolean forgetPassword( ForgetPasswordDto dto );

}
