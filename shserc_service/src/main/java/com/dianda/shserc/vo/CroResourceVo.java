package com.dianda.shserc.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * Resource vo object
 *
 * @author huachao
 * @since 2020-08-17
 */

@Data
public class CroResourceVo implements Serializable {
	
	private  long id;
	
	private  String title;
	
	private  String description;
	
	private  String author;
	
	private String authorCompany;
	
	private String authorEmail;
	
	
	private  String deformity;
	
	private  long deformityId;
	
	private  String state;
	
	private  String mediumType;
	
	private  String fileExtName;
	
	private  long  fileSize;
	
	private  long viewCount;
	
	private LocalDateTime addTime;
	
	private  String resourcePath;
	
	private String coverPath;
	
	private  String fileName;
	
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
	
	private  String importSource;

	private long importSourceId;
	
	private  String  schoolType;

	private long schoolTypeId;
	
	private String domain;

	private long domainId;
	
	private  String learnFrom;
	
	private long resourceTypeId;
	
	private  String resourceType;
	
	private long learnFromId;

	private long auditor;

	private String auditorOpinion;
	
	private  long  createTypeId;
	
	private String sourceUrl;

}

