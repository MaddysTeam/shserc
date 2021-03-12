package com.dianda.shserc.entity;

import lombok.Data;

@Data
public class ResPermission {

	private long id;

	//target types : menu, button, interface
	private long targetTypeId;

	//typeId: for example: button -> (show, enable),(show,disable)
	private long typeId;

	private String name;

	private String desc;

}

class Menu{}

class Button{}

class ServiceInterface{}
