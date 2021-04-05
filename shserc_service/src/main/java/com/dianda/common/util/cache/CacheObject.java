package com.dianda.common.util.cache;

import com.dianda.common.util.basic.ObjectUtil;
import lombok.Data;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class CacheObject {

	private String key;
	private Object value;
	private long expiredSeconds;
	private Date createTime;
	private Map<String, Object> map;
	private List<Object> list;
	private Map<String, Map<String, Object>> mapMap;
	private Map<String, List<Object>> mapList;
	private List<Map<String, Object>> listMap;
	
	public boolean isExpire( ) {
		if(ObjectUtil.isNull(createTime))
			return true;

		DateFormat dateFormat = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss" );
		try {
			Date currentTime = dateFormat.parse ( dateFormat.format ( new Date ( ) ) );
			long diff = currentTime.getTime ( ) - createTime.getTime ( );
			long seconds = diff / 1000;
			
			return seconds > expiredSeconds;
		} catch ( ParseException e ) {
			e.printStackTrace ( );
		}

		return false;
	}
	
}


