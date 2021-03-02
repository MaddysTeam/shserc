package com.dianda.shserc.exceptions;

import com.dianda.shserc.util.basic.StringUtil;

public class GlobalException extends RuntimeException implements IException {

	String code;
	String message;

	public GlobalException( String code, String message) {
		super(message);

		this.code = code;
		this.message = message;
	}

	public GlobalException(ExceptionType exceptionType) {
		this.code = StringUtil.toString(exceptionType.code);
	}
	
	public GlobalException(ExceptionType exceptionType,String message) {
		this.code = StringUtil.toString(exceptionType.code);
		this.message=message;
	}

	@Override
	public String getCode() {
		return this.code;
	}

	@Override
	public String getMessage() {
		return this.message;
	}


}


