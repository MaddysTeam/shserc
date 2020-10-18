package com.dianda.shserc.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
public class Resource {
	private  long id;
	
	private  String title;
	
	private  String author;
	
	private  String keywords;
	
	private  String description;
	
	private String coverPath;
	
	private  String resourcePath;
	
	private  String fileExtName;
	
	private  long  fileSize;
	
	private  boolean isLink;
	
	private  String authorCompany;
	
	private  String authorAddress;
	
	private String authorEmail;
	
	private String authorPhone;
	
	private  long deformityPKID;
	
	private  long viewCount;
	
	private  long starTotal;
	
	private  long auditor;
	
	private long auditorTime;
	
	private long addUser;
	
	private LocalDateTime  addTime;
	
	private  long updateUser;
	
	private LocalDateTime  updateTime;
	
	private  boolean isDeleted;
	
	private long deformity;
	
	private String deformityName;
	
	
	public boolean isNewOne(){
		return this.id<=0;
	}
	
}
