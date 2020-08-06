package com.dianda.core.util.basic;

/**
 *  @title BooleanUtil
 *  @Description Boolean helper
 *  @author huachao
 *  @Date 2020/2/23 10:52
 *  @Copyright 2019-2020
 */

public final class BooleanUtil {
	
	public static Boolean allTrue( boolean[] results) {
		boolean result = true;
		for (boolean r : results) {
			result = r;
		}
		
		return result;
	}
	
}
