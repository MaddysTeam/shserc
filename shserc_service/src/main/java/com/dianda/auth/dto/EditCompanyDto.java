package com.dianda.auth.dto;

import com.dianda.auth.common.Constant;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class EditCompanyDto {
	private  long id;
	private  long parentId;
	@NotBlank(message = Constant.Error.COMPANY_NAME_IS_REQUIRED )
	private  String companyName;
}
