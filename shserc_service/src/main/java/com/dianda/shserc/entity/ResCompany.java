package com.dianda.shserc.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.dianda.shserc.validators.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author huachao
 * @since 2020-08-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ResCompany extends  BaseEntity  {

	private static final long serialVersionUID = 1L;
	
	private String companyName;

	private Long parentId;

//	private Long addUser;
//
//	private LocalDateTime addTime;
//
//	private Long updateUser;
//
//	private LocalDateTime updateTime;
//
//	private int isDeleted;

//	public boolean isNewOne() {
//		return id <= 0;
//	}
}