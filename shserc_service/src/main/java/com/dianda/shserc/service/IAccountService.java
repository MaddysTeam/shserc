package com.dianda.shserc.service;

import com.dianda.shserc.dto.ChangePasswordDto;
import com.dianda.shserc.dto.ForgetPasswordDto;
import com.dianda.shserc.dto.LoginDto;


public interface IAccountService {
	LoginDto login( LoginDto login );
	
	boolean logout( );
	
	ChangePasswordDto changePassword( ChangePasswordDto dto );
	
	ForgetPasswordDto forgetPassword( ForgetPasswordDto dto );
}
