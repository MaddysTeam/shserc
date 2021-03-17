package com.dianda.shserc.dto;

import com.dianda.shserc.validators.NotNull;
import lombok.Data;

import javax.validation.constraints.Min;
import java.util.Map;

@Data
public class EditMenuRoleDto {
	
	@Min( value = 1 )
	long menuId;
	
	@NotNull
	private Map<Long, Long> menuRoleMap;
	
}
