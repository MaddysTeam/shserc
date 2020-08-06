package com.dianda.core.util.basic;

import java.util.Date;

public final class StringUtil {
	
	public static final String ToString( Object value) {
		if (value instanceof Integer || value instanceof Double || value instanceof Float || value instanceof Character || value instanceof Long || value instanceof Boolean || value instanceof Date
		) {
			return value + "";
		}
		
		return new String ();
	}
	
	public static final boolean IsNotNullOrEmpty( String value) {
		return null != value && !value.equals("");
	}
	
}


