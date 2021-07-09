package com.dianda.shsedu.dto;

import com.dianda.common.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@EqualsAndHashCode(callSuper = false)
public class EditColumnDto extends BaseDto {

	private  long id;

	@NotBlank
	private String title;

	@Min(0)
	long parentId;

	@Min(0)
	long statusId;

	boolean isMenu;

	@Min(0)
	int orderNo;
}
