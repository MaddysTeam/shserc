package com.dianda.shserc.dto;

import com.dianda.shserc.common.Constant;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class EditUserDto {

	private long id;

	@NotBlank(message = Constant.Error.USER_NAME_IS_REQUIRED)
	private String userName;

	@Min(value = 1,message = Constant.Error.COMPANY_ID_IS_REQUIRED)
	private long companyId;

	@NotBlank(message = Constant.Error.USER_IDCard_IS_REQUIRED)
	private String idCard;

	@NotBlank(message = Constant.Error.USER_REAL_NAME_IS_REQUIRED)
	private String realName;

	@NotBlank(message = Constant.Error.USER_MOBILE_IS_REQUIRED)
	private String mobile;

	@NotBlank(message = Constant.Error.USER_NAME_IS_REQUIRED)
	@Email(message = Constant.Error.EMAIL_FORMAT_INVALID)
	private String email;


}
