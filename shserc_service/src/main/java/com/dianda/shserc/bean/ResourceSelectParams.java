package com.dianda.shserc.bean;

import com.dianda.common.bean.BaseSelectParams;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
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
	long resourceTypeId;
	String title;
}
