package com.dianda.auth.entity;

import java.io.Serializable;
import java.util.Date;

import com.dianda.auth.validators.ICheckNotNullOrEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.shiro.SecurityUtils;

/**
 * <p>
 *
 * </p>
 *
 * @author huachao
 * @since 2020-08-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ICheckNotNullOrEmpty()
public class ResUser implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;

	private String userName;

	private String password;

//	private String passwordHash;

	private int isDeleted;

//	private String addUser;
//
//	private Date addDate;
//
//	private String updateUser;
//
//	private Date updateDate;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public boolean isNewOne() {
		return id <= 0;
	}

	public static ResUser getUserInfo(){
		ResUser current=(ResUser) SecurityUtils.getSubject().getPrincipal();
		return  current;
	}

}