package com.dianda.shserc.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class ResourceAuditDto{

	@Min(value = 1)
	private long resourceId;

	@Min(value = 0)
	private long auditorId;
	
	@Length( max=500)
	private String auditOpinion;
	
	private Boolean auditResult;

}
