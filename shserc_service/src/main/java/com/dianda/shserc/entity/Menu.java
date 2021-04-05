package com.dianda.shserc.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.dianda.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Menu extends BaseEntity {

	private long parentId;

	private int level;

	private String path;

	private String title;

	private long stateId;
	
	@TableField(exist = false)
	private  long roleId;

	@TableField(exist = false)
	private  String roleName;
	
}
