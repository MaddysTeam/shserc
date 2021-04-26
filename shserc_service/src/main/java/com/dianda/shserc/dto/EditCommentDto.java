package com.dianda.shserc.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class EditCommentDto {
	
	private long id;
	
	@Min ( value = 1)
	private long userId;
	
	@Min ( value = 0)
	private long auditTypeId;
	
	@NotBlank
	private String content;
	
	@Min ( value = 1)
	private long resourceId;
	
	@Min ( value = 0)
	private long auditor;
	
}
