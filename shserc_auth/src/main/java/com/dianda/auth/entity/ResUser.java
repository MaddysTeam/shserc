package com.dianda.auth.entity;

import java.io.Serializable;
import java.util.Date;

import com.dianda.auth.validators.ICheckNotNullOrEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author huachao
 * @since 2020-08-08
 */
@Data
@EqualsAndHashCode( callSuper = false )
@ICheckNotNullOrEmpty()
public class ResUser implements Serializable {
	
	private static final long serialVersionUID = 1L;
 
	private  String Id;
	
	private String userName;
	
	private String password;
	
	private String passwordHash;
	
	private int isDeleted;
	
	private  String addUser;
	
	private Date createDate;
	
	private  Date updateDate;
	
	private  String updateUser;
	
	
	public static long getSerialVersionUID( ) {
		return serialVersionUID;
	}
	
}