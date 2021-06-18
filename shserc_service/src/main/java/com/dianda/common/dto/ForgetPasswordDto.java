package com.dianda.common.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ForgetPasswordDto {

	@NotBlank
	private String mobile;

	@NotBlank
	private String newPassword;

	@NotBlank
	private String confirmPassword;

	private String message;

}
