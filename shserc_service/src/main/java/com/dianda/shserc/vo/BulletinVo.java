package com.dianda.shserc.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class BulletinVo implements Serializable {

	long id;

	String title;

	String content;

	long typeId;

	boolean top;

	private String filePath;


}

