package com.dianda.shserc.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Min;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode( callSuper = false )
public class ResourceOperation {
	
	@Min( value = 1 )
	private long resourceId;
	@Min( value = 1 )
	private long userId;

	private int operIntResult;

	private String operStringResult;

	private LocalDateTime addTime;
	
	private  String title;
	
	private  String author;
	
	private  String fileExtName;
	
	public ResourceOperation(){}

	public ResourceOperation( long resourceId , long userId ) {
		this.setResourceId ( resourceId );
		this.setUserId ( userId );
		this.setAddTime ( LocalDateTime.now ( ) );
	}
	
	public ResourceOperation( long resourceId , long userId ,int operIntResult, String operStringResult) {
		this.setResourceId ( resourceId );
		this.setUserId ( userId );
		this.setAddTime ( LocalDateTime.now ( ) );
		this.operIntResult=operIntResult;
		this.operStringResult=operStringResult;
	}
	
}

