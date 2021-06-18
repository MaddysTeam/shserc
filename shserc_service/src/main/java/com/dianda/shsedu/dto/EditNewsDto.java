package com.dianda.shsedu.dto;

import com.dianda.common.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@EqualsAndHashCode(callSuper = false)
public class EditNewsDto extends BaseDto {

	long id;

	@NotBlank
	String title;

	String content;

	@Min(value = 1)
	long typeId;

	@Min(value = 1)
	long statusId;

	@Min(value = 1)
	long columnId;

}
