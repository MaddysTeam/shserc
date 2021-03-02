package com.dianda.shserc.entity;

import lombok.Data;

@Data
public class ResPermission {

	private long id;
	private long targetId;
	private int type;
	private String name;
	private String desc;

}
