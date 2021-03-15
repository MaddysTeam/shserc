package com.dianda.shserc.entity;

import lombok.Data;

@Data
public class Menu{

	private long id;
	private long parentId;
	private String path;
	private String icon;
	private String title;
	private String targetArea;

}
