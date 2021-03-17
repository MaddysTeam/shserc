package com.dianda.shserc.dto;

import com.dianda.shserc.common.Constant;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
public class EditBulletinDto {

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
	
	private  long operatorId;
	
	private LocalDateTime operateDate;

}
