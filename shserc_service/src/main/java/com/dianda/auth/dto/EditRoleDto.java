package com.dianda.auth.dto;

import com.dianda.auth.common.Constant;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class EditRoleDto {
	private long id;

	@NotBlank(message= Constant.Error.ROLE_NAME_IS_REQUIRED)
	private String roleName;

	private String description;
}
