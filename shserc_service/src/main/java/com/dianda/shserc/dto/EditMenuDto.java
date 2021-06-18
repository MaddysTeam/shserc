package com.dianda.shserc.dto;

import com.dianda.common.common.Constant;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class EditMenuDto {
	
	@Min( value = 0 )
	private long id;
	
	@Min( value = 0 )
	private long parentId;
	
	@Min( value = 0 )
	private int level;
	
	@NotBlank
	@Length( min = Constant.LENGTH.MIN_PATH_LENGTH,
			max = Constant.LENGTH.MAX_PATH_LENGTH )
	private String path;
	
	@NotBlank
	@Length( min = Constant.LENGTH.MIN_NAME_LENGTH,
			max = Constant.LENGTH.MAX_NAME_LENGTH )
	private String title;
	
}
