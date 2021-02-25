package com.dianda.shserc.entity;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ResComment {

	private static final long serialVersionUID = 1L;

	public long id;

	public long userId;

	public long resourceId;

	public String content;

	public long auditTypeId;

	private long addUser;

	private LocalDateTime addTime;

	private long updateUser;

	private LocalDateTime updateTime;

	@TableField(exist = false)
	private String  auditType;
}
