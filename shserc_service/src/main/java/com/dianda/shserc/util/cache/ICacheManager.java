package com.dianda.shserc.util.cache;

import java.util.List;
import java.util.Map;

public interface ICacheManager<T> {
	
	T  getCache( String key );
	
	List<String> getKeysFromMap(String key) ;
	
	List<String> getKeysFromList(String key) ;

	void setCache( String key , T value , long expiredSeconds );
	
	boolean removeCache(String key);
	
	
}
