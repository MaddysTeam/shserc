package com.dianda.common.util.cache.manager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dianda.common.util.basic.ObjectUtil;
import com.dianda.common.util.basic.StringUtil;
import com.dianda.common.util.cache.CacheObject;
import com.dianda.common.util.nosql.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("redisCache")
public class RedisCacheManager implements ICacheManager {
	
	@Autowired
	RedisUtil redisUtil;
	
	@Override
	public CacheObject getCache( String key ) {
		String v = redisUtil.get ( key );
		if ( StringUtil.isNullOrEmpty ( v ) )
			return null;
		
		JSONObject jsonObject = JSONObject.parseObject ( v );
		CacheObject cacheObject = JSON.toJavaObject ( jsonObject , CacheObject.class );
		
		return cacheObject;
	}
	
	@Override
	public List<String> allKeys( ) {
		return null;
	}
	
	@Override
	public List<String> getKeysFromMap( String key ) {
		List<String> result = new ArrayList<> ();
		CacheObject cacheObject= getCache(key);
		if (ObjectUtil.isNull(cacheObject))
			return result;
		
		Map<String, Object> map = cacheObject.getMap();
		if (ObjectUtil.isNull(map) || map.size() <= 0)
			return result;
		
		result = map.keySet().stream().collect( Collectors.toList());
		
		return result;
	}
	
	@Override
	public List<String> getKeysFromList( String key ) {
		return null;
	}
	
	@Override
	public boolean setCache( String key , CacheObject value , long expiredSeconds ) {
		if ( ObjectUtil.isNull ( value ) )
			return false;
		
		if ( expiredSeconds > 2147483647 )
			expiredSeconds = 2147483647;
		
		if ( redisUtil.exists ( key ) )
			removeCache ( key );
		
		String val = JSONObject.toJSONString ( value );
		if ( expiredSeconds > 0 )
			redisUtil.saveNX ( key , val , ( int ) expiredSeconds );
		else
			redisUtil.saveNX ( key , val );
		
		return isExpire ( key );
	}
	
	@Override
	public boolean removeCache( String key ) {
		redisUtil.delKey ( key );
		return ! redisUtil.exists ( key );
	}
	
	@Override
	public boolean isExpire( String key ) {
		return ! redisUtil.exists ( key );
	}
}
