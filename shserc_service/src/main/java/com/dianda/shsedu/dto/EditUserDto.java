package com.dianda.shsedu.dto;

import com.dianda.common.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class EditUserDto extends BaseDto {

	private  long id;

	private String userName;

	private String realName;

	private long roleId;

	private long districtId;

	private  String email;

	private  String mobile;

	private String permissionIds;

}
