package com.dianda.shserc.dto;

import com.dianda.common.dto.BaseDto;
import com.dianda.common.common.Constant;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Min;

@Data
@EqualsAndHashCode(callSuper = false)
public class EditStateDto extends BaseDto {
	
	@Min( value = 1, message = Constant.Error.PARAMS_IS_INVALID )
	private long targetId;
	
	@Min( value = 0, message = Constant.Error.PARAMS_IS_INVALID )
	private long stateId;
	
	private boolean booleanState;
	
}
