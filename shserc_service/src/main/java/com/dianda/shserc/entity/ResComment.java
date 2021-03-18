package com.dianda.shserc.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class ResComment extends  BaseEntity {

	private static final long serialVersionUID = 1L;

	private long userId;

	private long resourceId;

	private String content;

	private long auditTypeId;

	private long auditor;

	@TableField(exist = false)
	private String auditType;

	@TableField(exist = false)
	private String auditorName;

	@TableField(exist = false)
	private String resourceTitle;
	
	@TableField(exist = false)
	private String addUserName;

}
