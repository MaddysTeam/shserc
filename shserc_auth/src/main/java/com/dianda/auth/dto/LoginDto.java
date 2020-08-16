package com.dianda.auth.dto;

import com.dianda.auth.validators.ICheckNotNullOrEmpty;
import com.dianda.auth.common.*;

import javax.validation.constraints.NotBlank;

import lombok.Data;


@Data
@ICheckNotNullOrEmpty()
public class LoginDto {
	
	@NotBlank( message = Constant.Error.USER_NAME_IS_REQUIRED )
	private String userName;
	@NotBlank(message = Constant.Error.PASSWORD_IS_REQUIRE)
	private String password;
	private Boolean isSuccess;
	private String token;
	private String message;
	
}
