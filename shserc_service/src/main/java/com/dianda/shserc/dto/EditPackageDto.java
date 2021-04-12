package com.dianda.shserc.dto;

import com.dianda.common.dto.BaseDto;
import com.dianda.shserc.common.Constant;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

@Data
@EqualsAndHashCode(callSuper = false)
public class EditPackageDto extends BaseDto {
	
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
	private String authorCompany;
	
	private String authorAddress;
	
	private String authorEmail;
	
	private String authorPhone;
	
	private long deformityId;
	
	private long subjectId;
	
	private long gradeId;
	
	private long stageId;
	
	private  long stateId;
	
	private long schoolTypeId;
	
	private long domainId;
	
	private long learnFromId;
	
}
