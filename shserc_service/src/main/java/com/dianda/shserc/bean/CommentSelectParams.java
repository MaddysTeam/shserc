package com.dianda.shserc.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CommentSelectParams extends BaseSelectParams {
	private long resourceId;
	private long auditor;
	private long auditTypeId;
}
