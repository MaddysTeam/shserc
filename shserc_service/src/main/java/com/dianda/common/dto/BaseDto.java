package com.dianda.common.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseDto {
	
	private long operatorId;
	
	private LocalDateTime operateDate;
	
}
