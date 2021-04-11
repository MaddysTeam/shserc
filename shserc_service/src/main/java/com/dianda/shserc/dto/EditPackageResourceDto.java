package com.dianda.shserc.dto;

import com.dianda.common.dto.BaseDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode( callSuper = false )
public class EditPackageResourceDto extends BaseDto {
	private long packageId;
	private long resourceId;
}

