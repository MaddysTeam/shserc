package com.dianda.auth.entity;

import java.io.Serializable;

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
public class ResUser implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String userName;
	
	private String password;
	
	private String passwordHash;
	
	public static long getSerialVersionUID( ) {
		return serialVersionUID;
	}
	
	public String getUserName( ) {
		return userName;
	}
	
	public void setUserName( String userName ) {
		this.userName = userName;
	}
	
	public String getPassword( ) {
		return password;
	}
	
	public void setPassword( String password ) {
		this.password = password;
	}
}