package com.dianda.shserc.util.cache.manager;

import com.alibaba.fastjson.JSONObject;
import com.dianda.shserc.util.cache.CacheObject;
import com.dianda.shserc.util.nosql.redis.RedisUtil;

import javax.annotation.Resource;
import java.util.List;

public class RedisCacheManager implements ICacheManager {

	@Resource
	RedisUtil redisUtil;

	@Override
	public CacheObject getCache(String key) {
		String v=redisUtil.get(key);
		JSONObject.parseObject(v).getObject(key, CacheObject.class);
		return null;
	}

	@Override
	public List<String> allKeys() {
		return null;
	}

	@Override
	public List<String> getKeysFromMap(String key) {
		return null;
	}

	@Override
	public List<String> getKeysFromList(String key) {
		return null;
	}

	@Override
	public void setCache(String key, CacheObject value, long expiredSeconds) {

	}

	@Override
	public boolean removeCache(String key) {
		 redisUtil.delKey(key);
		 return !redisUtil.exists(key);
	}

	@Override
	public boolean isExpire(String key) {
		return !redisUtil.exists(key);
	}
}
