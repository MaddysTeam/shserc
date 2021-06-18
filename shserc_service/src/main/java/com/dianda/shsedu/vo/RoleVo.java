package com.dianda.shsedu.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class RoleVo implements Serializable {

	private long id;

	private String roleName;

	private  String description;

	private  String permissionIds;

}
