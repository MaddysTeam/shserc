package com.dianda.common.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Interfaces extends  BaseEntity {
	
	 private  String name;
	 
	 private  String  path;

	 private  String project;
	 
	 private  String definition;
	
	private  double version;
	
	private  String  parameterType;
	
	private  String parameter;
	
	private  String responseValue;
	
	private  String method;

	public static double IncreaseVersion(double version){
		return version;
	}
}
