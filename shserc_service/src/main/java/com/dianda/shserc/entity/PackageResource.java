package com.dianda.shserc.entity;

import com.dianda.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PackageResource extends BaseEntity {
	
	private long packageId;
	
	private  long resourceId;
	
}
