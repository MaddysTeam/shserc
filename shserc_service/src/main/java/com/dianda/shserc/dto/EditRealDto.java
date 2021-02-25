package com.dianda.shserc.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class EditRealDto {

	@NotBlank
	private String cardNo;

	@NotBlank
	private String idCard;

	@NotBlank
	private long companyId;

	@NotBlank
	private String cardPassword;

	@NotBlank
	private String confrimPassword;
}
