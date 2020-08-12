package com.dianda.auth.service;

import com.dianda.auth.dto.ChangePasswordDto;
import com.dianda.auth.dto.LoginDto;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


public interface IAccountService {
	LoginDto login( LoginDto login );
	boolean logout();
	ChangePasswordDto changePassword(ChangePasswordDto dto);
}
