package com.dianda.auth.util.json;

public enum StatusType {
	OK("success"),
	UNAUTHORIZED("unAuthorized"),
	ERROR("error");
	
	private String status;
	
	StatusType( String status) {
		this.status = status;
	}
	
	
	public String getStatus() {
		return status;
	}
	
}
