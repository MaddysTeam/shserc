package com.dianda.shserc.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class EditRealDto {
	
	private long Id;

	@NotBlank
	private String cardNo;

	@NotBlank
	private String idCard;
	
	@Min(value = 1)
	private long companyId;
	
	@NotBlank
	private String realName;

	@NotBlank
	private String cardPassword;
	
}
