package com.dianda.shserc.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.dianda.shserc.validators.ICheckNotNullOrEmpty;
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
@ICheckNotNullOrEmpty()
public class ResCompany implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;

	private String companyName;

	private Long parentId;

	private Long addUser;

	private LocalDateTime addDate;

	private Long updateUser;

	private LocalDateTime updateDate;

	private int isDeleted;

	public boolean isNewOne() {
		return id <= 0;
	}
}