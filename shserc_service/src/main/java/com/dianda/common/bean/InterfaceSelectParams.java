package com.dianda.common.bean;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class InterfaceSelectParams extends BaseSelectParams {
	private long roleId;
	private  String version;
}
