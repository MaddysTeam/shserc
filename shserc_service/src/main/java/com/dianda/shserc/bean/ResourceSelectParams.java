package com.dianda.shserc.bean;

import lombok.Data;

@Data
public class ResourceSelectParams extends BaseSelectParams {
	long resourceId;
	long deformityId;
	long stateId;
	String title;
}
