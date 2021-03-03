package com.dianda.shserc.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class EditRealDto {
	
	private long Id;

	@NotBlank
	private String cardNo;

	@NotBlank
	private String idCard;
	
	
	private long companyId;
	
	@NotBlank
	private String realName;

	@NotBlank
	private String cardPassword;

	//@NotBlank
	//private String confrimPassword;
}
