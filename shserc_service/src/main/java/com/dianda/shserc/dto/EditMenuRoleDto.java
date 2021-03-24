package com.dianda.shserc.dto;

import com.dianda.shserc.common.Constant;
import com.dianda.shserc.validators.NotNull;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Map;

@Data
public class EditMenuRoleDto {

	long menuId;
	
	@NotNull
	@Size(min = 1,message = Constant.Error.MENU_ROLE_IS_REQUIRED)
	private Map<Long, Long> menuRoleMap;
	
}
