package com.dianda.common.dto;

import com.dianda.common.common.Constant;
import com.dianda.common.validators.NotNull;

import javax.validation.constraints.NotBlank;

import lombok.Data;


@Data
@NotNull
public class LoginDto {
	
	@NotBlank( message = Constant.Error.USER_NAME_IS_REQUIRED )
	private String userName;
	@NotBlank(message = Constant.Error.PASSWORD_IS_REQUIRE)
	private String password;
	
	private Boolean isSuccess;
	private String token;
	private String message;
	
}
