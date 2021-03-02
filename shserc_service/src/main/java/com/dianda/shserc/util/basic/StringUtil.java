package com.dianda.shserc.util.basic;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;

import java.util.Date;

public final class StringUtil {
	
	public static final String toString( Object value ) {
		if ( value instanceof Integer || value instanceof Double || value instanceof Float || value instanceof Character || value instanceof Long || value instanceof Boolean || value instanceof Date
		) {
			return value + "";
		}
		
		return new String ( );
	}
	
	public static final boolean isNullOrEmpty( String value ) {
		return null == value || "" == value || StringUtils.isNotBlank(value);
	}
	
}


