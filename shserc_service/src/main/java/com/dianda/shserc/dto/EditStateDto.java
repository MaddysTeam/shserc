package com.dianda.shserc.dto;

import com.dianda.shserc.common.Constant;
import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class EditStateDto {

	@Min(value = 1,message = Constant.Error.PARAMS_IS_INVALID)
	private long targetId;

	@Min(value = 1,message = Constant.Error.PARAMS_IS_INVALID)
	private long stateId;

}
