package com.dianda.shserc.entity;

import java.io.Serializable;

import com.dianda.shserc.validators.ICheckNotNullOrEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * user entity
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
	
	//private int CardNo;
	
	// private  int CardPassword;
	
	private int isDeleted;
	
	private  Long  companyId;
	
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public boolean isNewOne() {
		return id <= 0;
	}
	
}