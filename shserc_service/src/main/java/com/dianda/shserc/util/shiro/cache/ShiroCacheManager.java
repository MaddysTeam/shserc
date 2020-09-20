package com.dianda.shserc.util.shiro.cache;

import com.dianda.shserc.util.shiro.jwt.JwtSettings;
import  com.dianda.shserc.util.nosql.redis.RedisUtil;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShiroCacheManager implements CacheManager {
	
	@Autowired
	RedisUtil redisUtil;
	
	@Autowired
	JwtSettings jwtSettings;
	
	@Override
	public <K, V> Cache<K, V> getCache( String name) throws CacheException {
		return new ShiroCache(redisUtil,  jwtSettings);
	}
	
}
