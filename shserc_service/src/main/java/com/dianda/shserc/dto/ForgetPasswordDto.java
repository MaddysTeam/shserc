package com.dianda.shserc.dto;

import lombok.Data;

@Data
public class ForgetPasswordDto {
	
	private  String  mobile;
	
	private  String  newPassword;
	
	private  boolean isSuccess;
	
	private  String message;
}
