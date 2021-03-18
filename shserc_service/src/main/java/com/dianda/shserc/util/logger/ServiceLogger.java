package com.dianda.shserc.util.logger;


import com.dianda.shserc.util.basic.ObjectUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ServiceLogger implements IServiceLogger {

	static Logger logger = (Logger) LoggerFactory.getLogger(ServiceLogger.class);

	@Override
	public void fatal(LoggerMessage message) {
		LogLevel logLevel= LogLevel.FATAL;
		if (!ObjectUtil.isNull(message))
			logger.error(message.toString());
	}

	@Override
	public void error(LoggerMessage message) {
		if (!ObjectUtil.isNull(message))
			logger.error(message.toString());
	}

	@Override
	public void warn(LoggerMessage message) {
		if (!ObjectUtil.isNull(message))
			logger.warn(message.toString());
	}

	@Override
	public void info(LoggerMessage message) {
		if (!ObjectUtil.isNull(message))
			logger.info(message.toString());
	}

	@Override
	public void system(LoggerMessage message) {
		if (!ObjectUtil.isNull(message))
			logger.info(message.toString());
	}

}
