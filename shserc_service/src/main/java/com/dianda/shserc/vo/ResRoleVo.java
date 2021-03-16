package com.dianda.shserc.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResRoleVo implements Serializable {
	
	private  long id;
	private  String roleName;
	private  String description;

}

