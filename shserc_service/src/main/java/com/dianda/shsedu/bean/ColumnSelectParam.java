package com.dianda.shsedu.bean;

import com.dianda.common.bean.BaseSelectParams;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ColumnSelectParam extends BaseSelectParams {
	private long ownerId;
	private long parentId;
}
