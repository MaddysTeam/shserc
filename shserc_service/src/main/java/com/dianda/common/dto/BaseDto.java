package com.dianda.common.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class BaseDto {
	
	private long operatorId;
	
	private Date operateDate;
	
}
