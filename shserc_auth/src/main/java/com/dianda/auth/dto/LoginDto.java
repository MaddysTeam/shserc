package com.dianda.auth.dto;

public class LoginDto {
	
	private String userName;
	private String password;
	private Boolean IsLoginSuccess;
	private String token;
	private String message;
	
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
	
	public Boolean getLoginSuccess( ) {
		return IsLoginSuccess;
	}
	
	public void setLoginSuccess( Boolean loginSuccess ) {
		IsLoginSuccess = loginSuccess;
	}
	
	public String getToken( ) {
		return token;
	}
	
	public void setToken( String token ) {
		this.token = token;
	}
	
	public String getMessage( ) {
		return message;
	}
	
	public void setMessage( String message ) {
		this.message = message;
	}
	
	
}
