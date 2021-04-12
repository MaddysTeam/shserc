package com.dianda.shserc.entity;

import lombok.Data;

import javax.validation.constraints.Min;
import java.time.LocalDateTime;

@Data
public class PackageOperation  {
	
	@Min( value = 1 )
	private long packageId;
	
	@Min( value = 0 )
	private long resourceId;
	
	@Min( value = 1 )
	private long userId;
	
	private int operIntResult;
	
	private String operStringResult;
	
	private LocalDateTime addTime;
}
