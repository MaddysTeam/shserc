package com.dianda.shsedu.entity;

import com.dianda.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Permission extends BaseEntity {

	private String name;

	private String url;

	private String description;

	private long sort;

	private long parentId;
	
	private boolean isMenu;

}
