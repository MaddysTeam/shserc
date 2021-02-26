package com.dianda.shserc.dto;

import lombok.Data;

@Data
public class EditCommentDto {
	private long id;
	private long userId;
	private long auditTypeId;
	private String content;
	private long resourceId;
}
