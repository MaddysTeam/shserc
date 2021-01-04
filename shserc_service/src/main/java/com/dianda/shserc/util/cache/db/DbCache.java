package com.dianda.shserc.util.cache.db;

import com.dianda.shserc.util.cache.CacheObject;
import com.dianda.shserc.util.cache.manager.ICacheManager;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.List;

public class DbCache {

	@Resource( name = "memoryCache" )
	ICacheManager manager;
	
	public CacheObject getCacheFromTable( String table ) {
		return ( CacheObject ) manager.getCache ( table );
	}
	
	public CacheObject getCacheFromSql( String sql ) {
		return ( CacheObject ) manager.getCache ( sql );
	}
	
	public void setCacheFromTable( String table , CacheObject value , long expires ) {
//		value.setKey ( table );
//		manager.setCache ( table , value , expires );
	}
	
	public void setCacheFromSql( String sql , String[] includeTables , CacheObject value , long expires ) {
//		value.setKey ( sql );
//		//value.setMap (  );
//		manager.setCache ( sql , value , expires );
	}
	
	public boolean removeCache( String table ) {
		List<String> keys = manager.getKeysFromList ( table );
		
		if(keys.size ()<=0)
			keys=manager.getKeysFromMap ( table );
		
		if(keys.size ()>0)
			for(String key : keys){
				manager.removeCache ( key );
			}
		
		return true;
	}
	
}
