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
public class ResourceVo  implements Serializable {
	
	private  long id;
	
	private  String title;
	
	private  String author;
	
	private  String resourceType;
	
	private  String deformity;
	
	private  long deformityId;
	
	private  String state;
	
	private  String mediumType;
	
	private  String fileExtName;
	
	private  long  fileSize;
	
	private String authorEmail;
	
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

	private long subjectId;

	private long gradeId;

	private long stageId;

	private long importSourceId;

	private long schoolTypeId;

	private long domainId;

	private long auditor;

	private String auditorOpinion;

}

