package com.dianda.shserc.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class PackageVo implements Serializable {
	
	private  long id;
	
	private  String title;
	
	private  String description;
	
	private  String author;
	
	private String authorCompany;
	
	private String authorEmail;
	
	private  String deformity;
	
	private  long deformityId;
	
	private  String state;
	
	private  long viewCount;
	
	private LocalDateTime addTime;
	
	private String coverPath;

	private  String keywords;
	
	private  int starTotal;
	
	private  int starCount;
	
	private  int downloadCount;
	
	private  int favoriteCount;
	
	private  int commentCount;
	
	private String subject;
	
	private long subjectId;
	
	private String grade;
	
	private long gradeId;
	
	private  String stage;
	
	private long stageId;
	
	private long stateId;
	
	private  String  schoolType;
	
	private long schoolTypeId;
	
	private String domain;
	
	private long domainId;
	
	private  String learnFrom;
	
	private long learnFromId;
	
	private long auditor;
	
	private String auditorOpinion;
}
