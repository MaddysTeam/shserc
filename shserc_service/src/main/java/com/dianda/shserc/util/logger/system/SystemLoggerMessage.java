package com.dianda.shserc.util.logger.system;

import com.dianda.shserc.util.logger.LoggerMessage;

import java.time.LocalDateTime;

public class SystemLoggerMessage extends LoggerMessage {

	public SystemLoggerMessage(String userName, String className, String method, Object[] paras) {
		super(userName, LocalDateTime.now ().toString () , className, method);
	}

	@Override
	public String toString() {
		return super.toString();
	}

}
