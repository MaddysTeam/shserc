package com.dianda.shsedu.dto;

import com.dianda.common.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@EqualsAndHashCode(callSuper = false)
public class EditPermissionDto extends BaseDto {
	private long id;
	private long sort;

	@Min(1)
	private long parentId;

	@NotBlank
	private String name;
	private String description;

	@NotBlank
	private String url;
}
