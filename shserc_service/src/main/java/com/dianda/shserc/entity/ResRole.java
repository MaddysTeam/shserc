package com.dianda.shserc.entity;

import com.dianda.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ResRole
 *
 * @author huachao
 * @since 2020-08-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ResRole extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	private String roleName;

	private String description;
	
}