package com.dianda.shserc.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class ResComment {

	private static final long serialVersionUID = 1L;
	
	private long id;
	
	private long userId;
	
	private long resourceId;
	
	private String content;
	
	private long auditTypeId;
	
	@TableField( exist = false )
	private String addUserName;
	
	@TableField( exist = false )
	private  String resourceTitle;

	private long addUser;

	private LocalDateTime addTime;

	private long updateUser;

	private LocalDateTime updateTime;

	@TableField(exist = false)
	private String  auditType;
	
	public boolean isNewOne( ) {
		return this.id <= 0;
	}
	
}
