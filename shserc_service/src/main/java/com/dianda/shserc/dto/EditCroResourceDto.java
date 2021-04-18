package com.dianda.shserc.dto;

import com.dianda.common.dto.BaseDto;
import com.dianda.shserc.common.Constant;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

@Data
@EqualsAndHashCode(callSuper = false)
public class EditCroResourceDto extends BaseDto {

	private long id;
	
	@NotBlank
	private String title;
	
	@NotBlank
	private String author;
	
	@NotBlank
	private String keywords;
	
	private String description;
	
	private String coverPath;
	
	@NotBlank(message = Constant.Error.FILE_UPLOAD_DST_PATH_IS_REQUIRED )
	private String resourcePath;

	@NotBlank(message = Constant.Error.FILE_EXT_TYPE_IS_REQUIRED)
	private String fileExtName;
	
	private long fileSize;

	@NotBlank(message = Constant.Error.FILE_NAME_IS_REQUIRED)
	private String fileName;
	
	private boolean isLink;

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

	private long importSourceId;

	private long schoolTypeId;

	private long domainId;

	private long learnFromId;

	private long resourceTypeId;

}

