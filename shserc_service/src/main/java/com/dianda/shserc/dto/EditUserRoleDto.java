package com.dianda.shserc.dto;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class EditUserRoleDto {

	@Min(value = 1)
	private long userId;

	@Min(value = 1)
	private long roleId;

}
