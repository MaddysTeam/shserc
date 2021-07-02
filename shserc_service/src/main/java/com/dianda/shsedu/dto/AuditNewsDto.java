package com.dianda.shsedu.dto;

import com.dianda.common.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Min;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class AuditNewsDto extends BaseDto {
	@Min(value = 1)
	long id;

	Date auditTime;

	@Min(value = 1)
	private long auditorId;

	@Min(value = 1)
	long statusId;

}
