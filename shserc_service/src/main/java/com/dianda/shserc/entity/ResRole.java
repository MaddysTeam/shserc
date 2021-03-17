package com.dianda.shserc.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * ResRole
 *
 * @author huachao
 * @since 2020-08-12
 */
@Data
public class ResRole extends BaseEntity {

	private static final long serialVersionUID = 1L;
	
	private String roleName;

	private String description;
	
}