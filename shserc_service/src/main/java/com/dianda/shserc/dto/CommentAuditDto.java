package com.dianda.shserc.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;

@Data
public class CommentAuditDto {

	@Min(value=1)
	private  long resourceId;
	
	@Min(value = 1)
	private long commentId;

	@Min(value = 0)
	private long auditorId;
	
	@Length( max=500)
	private String auditOpinion;
	
	private Boolean auditResult;

}
