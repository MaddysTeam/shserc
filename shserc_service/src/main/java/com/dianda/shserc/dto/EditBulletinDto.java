package com.dianda.shserc.dto;

import com.dianda.common.dto.BaseDto;
import com.dianda.common.common.Constant;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@EqualsAndHashCode(callSuper = false)
public class EditBulletinDto  extends BaseDto {

	@Min(value = 0)
	private long Id;

	@Min(value = 0,message = Constant.Error.BULLETIN_TYPE_IS_NOT_CORRECT)
	private long typeId;

	@NotBlank(message = Constant.Error.BULLETIN_TITLE_IS_REQUIRE)
	private String title;

	@NotBlank(message = Constant.Error.BULLETIN_CONTENT_IS_REQUIRE)
	@Length(max=10000,message = Constant.Error.BULLETIN_CONTENT_MAX_LENGTH)
	private String content;

	private boolean top;

	private String filePath;

	private String fileName;

	private String fileExtName;

	private long fileSize;
	
//	private  long operatorId;
//
//	private LocalDateTime operateDate;

}


