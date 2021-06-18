package com.dianda.shsedu.dto;

import com.dianda.common.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class EditColumnDto extends BaseDto {

	private  long id;

	private String title;

	private boolean isMenu;

	long parentId;

}
