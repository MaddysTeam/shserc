package com.dianda.auth.dto;

import com.dianda.auth.common.Constant;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class EditUserDto {
	
	private  String id;
	
	@NotBlank(message = Constant.Error.USER_NAME_IS_REQUIRED)
	private String userName;
	
	private int isDeleted;
	
}
