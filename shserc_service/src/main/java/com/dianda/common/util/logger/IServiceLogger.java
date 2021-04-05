package com.dianda.common.util.logger;

/**
 *  @title IServiceLogger
 *  @Description  definition methods of logger
 *  @author huachao
 *  @Date 2020/10/17 13:45
 *  @Copyright 2019-2020
 */

public interface IServiceLogger {
	void fatal(LoggerMessage message);
	void error(LoggerMessage message);
	void warn(LoggerMessage message);
	void info(LoggerMessage message);
	void system(LoggerMessage message);
}

