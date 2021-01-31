package com.dianda.shserc.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Min;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode( callSuper = false )
public class ResourceOperation {
	
	@Min( value = 1 )
	private long resourceId;
	@Min( value = 1 )
	private long userId;
	
	private LocalDateTime addTime;
	
	public ResourceOperation( long resourceId , long userId ) {
		this.setResourceId ( resourceId );
		this.setUserId ( userId );
		this.setAddTime ( LocalDateTime.now ( ) );
	}
	
}
