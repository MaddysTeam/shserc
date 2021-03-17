package com.dianda.shserc.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseEntity {
	
	private long id;
	
	private long addUser;
	
	private LocalDateTime addTime;
	
	private long updateUser;
	
	private LocalDateTime updateTime;
	
	private int isDeleted;
	
	public boolean isNewOne() {
		return this.id <= 0;
	}
	
}
