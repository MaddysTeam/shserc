package com.dianda.shserc.dto;

import com.dianda.shserc.common.Constant;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class EditUserDto {

	private long id;

	@NotBlank(message = Constant.Error.USER_NAME_IS_REQUIRED)
	private String userName;

	private int isDeleted;

	private long[] roleIds;

}
