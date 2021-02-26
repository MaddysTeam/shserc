package com.dianda.shserc.bean;

import lombok.Data;

@Data
public class ResourceSelectParams extends BaseSelectParams {
	long userId;
	long resourceId;
	long deformityId;
	long stateId;
	long subjectId;
	long domainId;
	long importSourceId;
	long gradeId;
	long stageId;
	String title;
}
