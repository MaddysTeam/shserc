package com.dianda.shsedu.entity;

import com.dianda.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Columns extends BaseEntity {
	
	String title;

	long parentId;
	
	long orderNo;

	long statusId;

	boolean isMenu;

	//boolean isAudit;
	
}
