package com.dianda.resource.util.shiro.cache;

import com.dianda.resource.util.shiro.jwt.JwtConstant;
import com.dianda.resource.util.shiro.jwt.JwtOperation;
import com.dianda.resource.util.shiro.jwt.JwtSettings;
import com.dianda.resource.util.nosql.redis.RedisUtil;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author hauchao
 * @title redis cache for shiro
 * @Date 2020/3/8 20:54
 * @Copyright 2019-2020
 */


public class ShiroCache<K, V> implements Cache<K, V> {
	
	RedisUtil redisUtil;
	JwtSettings jwtSettings;
	
	public ShiroCache(RedisUtil redisUtil, JwtSettings jwtSettings) {
		this.redisUtil = redisUtil;
		this.jwtSettings = jwtSettings;
	}
	
	@Override
	public Object get( Object key) throws CacheException {
		Object result=null;
		String k = this.getKey(key);
		if (redisUtil.exists(k))
			result= redisUtil.get(k);
		
		return result;
	}
	
	@Override
	public Object put( Object key, Object value) throws CacheException {
		String k=getKey(key);
		redisUtil.saveObject(k,value, jwtSettings.expiredSeconds);
		
		return get(k);
	}
	
	@Override
	public Object remove( Object key) throws CacheException {
		String k=this.getKey(key);
		if(redisUtil.exists(k))
			redisUtil.delKey(k);

		return null;
	}
	
	@Override
	public void clear() throws CacheException {
	}
	
	@Override
	public int size() {
		return 100;
	}
	
	@Override
	public Set<K> keys() {
		return null;
	}
	
	@Override
	public Collection<V> values() {
	  	Set<K> keys= this.keys();
	  	List<V> values= new ArrayList ();
	  	for ( Object k :  keys){
	  			values.add((V)redisUtil.get( getKey(k)));
		}
	  	
	  	return values;
	}
	
	private String getKey( Object key) {
		return JwtConstant.CACHE_PREFIX + JwtOperation.getClaim(key.toString(), JwtConstant.ACCOUNT);
	}
	
}
