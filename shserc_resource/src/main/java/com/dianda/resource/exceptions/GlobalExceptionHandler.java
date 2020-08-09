package com.dianda.resource.exceptions;

import com.dianda.resource.util.constant.ExceptionConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(value = NullPointerException.class)
	@ResponseBody
	public GlobalException exceptionHandler(HttpServletRequest request, NullPointerException e){
		logger.error(ExceptionConstant.NULL_POINTER);
		return new GlobalException(ExceptionType.SERVER_ERROR);
	}

}
