package com.dianda.shserc.exceptions;

public enum ExceptionType{
   
   SERVER_ERROR(500), NOT_FOUND(404) {};
   
	int code;
 
	private ExceptionType(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	
}
