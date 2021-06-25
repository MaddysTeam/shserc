package com.dianda.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.dianda.common.util.basic.ObjectUtil;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class BaseEntity {

	@TableId(value = "id",type= IdType.AUTO)
	private long id;
	
	private long addUser;
	
	private Date addTime;
	
	private long updateUser;
	
	private Date updateTime;
	
	private int isDeleted;
	
	public boolean isNewOne() {
		return this.id <= 0 ;
	}
	
}
