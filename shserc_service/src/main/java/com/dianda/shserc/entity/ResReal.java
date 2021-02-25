package com.dianda.shserc.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * real card info entity
 *
 * @author huachao
 * @since 2020-10-05
 */
@Data
public class ResReal {

	private static final long serialVersionUID = 1L;

	private long id;

	private String realName;

	private String cardNo;

	private String idCard;

	private String cardPassword;

	private long stateId;

	private long companyId;

	private String company;

	private LocalDateTime birthday;

	private String State;

	private long addUser;

	private LocalDateTime addTime;

	private long updateUser;

	private LocalDateTime updateTime;

	public boolean isNewOne() {
		return this.id <= 0;
	}

}
