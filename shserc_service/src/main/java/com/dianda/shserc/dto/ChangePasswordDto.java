package com.dianda.shserc.dto;

import com.dianda.shserc.common.Constant;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 *  @title ChangePasswordDto
 *  @Description Dto class only for change password
 *  @author huachao
 *  @Date 2020/8/15 11:26
 *  @Copyright 2019-2020
 */

@Data
public class ChangePasswordDto {
	
	private  String userName;
	
	// old password
	@NotBlank( message = Constant.Error.PASSWORD_IS_REQUIRE )
	private String oldPassword;
	
	// password
	@NotBlank( message = Constant.Error.PASSWORD_IS_REQUIRE )
	@Pattern(regexp = Constant.Regex.PASSWORD,message = Constant.Error.PASSWORD_NOT_VALID)
	private String newPassword;

	// password
	@NotBlank( message = Constant.Error.PASSWORD_IS_REQUIRE )
	@Pattern(regexp = Constant.Regex.PASSWORD,message = Constant.Error.PASSWORD_NOT_VALID)
	private String confirmPassword;
	
	private Boolean isSuccess;
	private  String message;
	
}
