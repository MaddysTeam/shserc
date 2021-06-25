package com.dianda.common.util.basic;

import java.util.Date;
import java.util.TimeZone;

public class DateTimeUtil {

	public static final Date now(){
		TimeZone timeZone=TimeZone.getTimeZone("Etc/GMT-8");
		TimeZone.setDefault(timeZone);
		return new Date();
	}
}
