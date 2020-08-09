package com.dianda.resource.exceptions;

import com.dianda.resource.util.basic.StringUtil;

public class GlobalException extends RuntimeException implements IException {

	String code;
	String message;

	public GlobalException( String code, String message) {
		super(message);

		this.code = code;
		this.message = message;
	}

	public GlobalException(ExceptionType exceptionType) {
		this.code = StringUtil.ToString(exceptionType.code);
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


