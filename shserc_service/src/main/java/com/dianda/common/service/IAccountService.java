package com.dianda.common.service;

import com.dianda.common.dto.ChangePasswordDto;
import com.dianda.common.dto.ForgetPasswordDto;
import com.dianda.common.dto.LoginDto;

import javax.validation.Valid;


public interface IAccountService {

	boolean login(@Valid LoginDto login );
	
	boolean logout( );
	
	boolean changePassword(@Valid ChangePasswordDto dto );
	
	boolean forgetPassword( ForgetPasswordDto dto );

}
