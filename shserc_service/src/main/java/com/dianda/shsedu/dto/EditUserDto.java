package com.dianda.shsedu.dto;

import com.dianda.common.common.Constant;
import com.dianda.common.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

@Data
@EqualsAndHashCode(callSuper = false)
public class EditUserDto extends BaseDto {

	private  long id;

	@NotBlank
	private String userName;
	@NotBlank
	private String password;;

	private String realName;

	@Min(value = 0)
	private long roleId;

	@Min(value = 0)
	private long districtId;

	@Null
	@Email(message = Constant.Error.EMAIL_FORMAT_INVALID)
	private  String email;

	private  String mobile;

	private String permissionIds;

}
