package com.dianda.shserc.bean;

import lombok.Data;

@Data
public class CommentSelectParams extends BaseSelectParams {
	private long resourceId;
	private long userId;
	private long auditTypeId;
}
