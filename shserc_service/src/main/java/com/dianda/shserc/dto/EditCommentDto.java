package com.dianda.shserc.dto;

import com.dianda.common.dto.BaseDto;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class EditCommentDto extends BaseDto {
	
	private long id;
	
	@Min ( value = 1)
	private long userId;
	
	@NotBlank
	private String content;
	
	@Min ( value = 1)
	private long resourceId;
	
	@Min ( value = 0)
	private long auditor;
	
}
