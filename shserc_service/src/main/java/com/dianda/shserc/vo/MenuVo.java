package com.dianda.shserc.vo;

import lombok.Data;

import java.util.ArrayList;

@Data
public class MenuVo {

	private  long id;

	private  long parentId;

	private  String name;

	private  String path;

	private int level;

	private long stateId;

	private ArrayList<ResRoleVo> roles;

	private long roleId;

	private String roleName;

}
