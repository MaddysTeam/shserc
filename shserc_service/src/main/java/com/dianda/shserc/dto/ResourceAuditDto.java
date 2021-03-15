package com.dianda.shserc.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class ResourceAuditDto{

	@Min(value = 1)
	private long resourceId;

	@Min(value = 1)
	private long auditorId;

	@NotBlank
	private String auditOpinion;

	@Min(value = 1)
	private long stateId;

}
