package com.dianda.shserc.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class CommentVo implements Serializable {
	private long id;
	private long resourceId;
	private String auditType;
	private String content;
}
