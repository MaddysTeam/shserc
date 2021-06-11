package com.dianda.shsedu.dto;

import com.dianda.common.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class EditNewsDto extends BaseDto {

	long newsId;

	String title;

	String content;

	long typeId;

	long statusId;

	long columnId;

}
