package com.dianda.shserc.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class EditBulletinDto {

	@Min(value = 0)
	private long Id;

	@Min(value = 1)
	private long addUser;

	@Min(value = 0)
	private long typeId;

	@NotBlank
	private String title;

	@NotBlank
	@Length(max=10000)
	private String content;

	private boolean isTop;

}
