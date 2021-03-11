package com.dianda.shserc.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class ResUserRole {
	
	private  long roleId;

	private  long userId;

	@TableField(exist = false)
	private  String roleName;

}

