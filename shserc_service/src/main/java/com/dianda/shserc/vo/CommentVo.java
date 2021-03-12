package com.dianda.shserc.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class CommentVo implements Serializable {
	private long id;
	private long resourceId;
	private String auditorName;
	private String auditType;
	private String content;
	private String addUserName;
	private String resourceTitle;
	private LocalDateTime commentTime;
}
