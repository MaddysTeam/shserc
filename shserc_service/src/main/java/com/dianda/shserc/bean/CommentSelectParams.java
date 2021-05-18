package com.dianda.shserc.bean;

import com.dianda.common.bean.BaseSelectParams;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CommentSelectParams extends BaseSelectParams {
	private long resourceId;
	private long auditor;
	private long stateId;
}
