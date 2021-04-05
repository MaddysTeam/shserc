package com.dianda.common.entity;

import lombok.Data;

@Data
public class Interfaces extends  BaseEntity {
	
	 private  String name;
	 
	 private  String  path;
	 
	 private  String definition;
	
	private  String version;
	
	private  String  parameterType;
	
	private  String parameter;
	
	private  String responseValue;
	
	private  String method;
}
