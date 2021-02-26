package com.dianda.shserc.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class EditResourceDto {
	private long id;
	
	@NotBlank
	private String title;
	
	@NotBlank
	private String author;
	
	@NotBlank
	private String keywords;
	
	private String description;
	
	private String coverPath;
	
	@NotBlank
	private String resourcePath;
	
	private String fileExtName;
	
	private long fileSize;
	
	private String fileName;
	
	private boolean isLink;
	
	private String authorCompany;
	
	private String authorAddress;
	
	private String authorEmail;
	
	private String authorPhone;
	
	private long deformityId;
	
	private long auditor;

	private long subjectId;

	private long gradeId;

	private long stageId;

	private long importSourceId;

	private long schoolTypeId;

	private long domainId;
	
}

