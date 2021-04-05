package com.dianda.common.util.cache.manager;

import com.dianda.common.util.cache.CacheObject;

import java.util.List;

public interface ICacheManager {

	CacheObject getCache( String key );

	List<String> allKeys();
	
	List<String> getKeysFromMap(String key) ;
	
	List<String> getKeysFromList(String key) ;

	boolean setCache( String key , CacheObject value , long expiredSeconds );
	
	boolean removeCache(String key);
	
	boolean isExpire(String key);
}
