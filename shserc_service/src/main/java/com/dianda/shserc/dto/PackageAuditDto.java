package com.dianda.shserc.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;

@Data
public class PackageAuditDto {

	@Min(value = 1)
	private long packageId;

	@Min(value = 0)
	private long auditorId;
	
	@Length( max=500)
	private String auditOpinion;
	
	private Boolean auditResult;

}
