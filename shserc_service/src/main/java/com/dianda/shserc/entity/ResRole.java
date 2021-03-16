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
@EqualsAndHashCode(callSuper = false)
public class ResRole implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;

	private String roleName;

	private String description;

	private long addUser;

	private LocalDateTime addTime;

	private long updateUser;

	private LocalDateTime updateTime;

	private boolean isDeleted;

	public boolean isNewOne() {
		return id <= 0;
	}

}