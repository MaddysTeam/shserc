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
	
	private int CardNo;
	
	private  int CardPassword;
	
	private int isDeleted;
	
	private  Long  companyId;
	
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public boolean isNewOne() {
		return id <= 0;
	}
	
}