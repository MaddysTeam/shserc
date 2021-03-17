package com.dianda.shserc.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class Menu {

	private long id;

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
