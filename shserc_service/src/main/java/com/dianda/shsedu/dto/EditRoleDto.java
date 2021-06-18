package com.dianda.shsedu.dto;

import com.dianda.common.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class EditRoleDto extends BaseDto {
	long id;
	String roleName;
	String description;
	String permissionIds;
}
