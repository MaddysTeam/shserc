package com.dianda.shserc.util.cache.manager;

import com.dianda.shserc.exceptions.GlobalException;
import com.dianda.shserc.util.basic.ObjectUtil;
import com.dianda.shserc.util.cache.CacheObject;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * memory cache manager
 *
 * @author huachao
 * @since 2020-08-17
 */
@Service("memoryCache")
public class MemoryCacheManager implements ICacheManager {

	static List<CacheObject> cacheObjects = new ArrayList<>();

	@Override
	public CacheObject getCache(String key) {
		CacheObject result = null;
		for (CacheObject o : cacheObjects) {
			if (o.getKey().equals(key) || o.getKey() == key) {
				result = o;
				break;
			}
		}
		if (!ObjectUtil.isNull(result) && result.isExpire()) {
			result = null;
			removeCache(key);
		}

		return result;
	}

	@Override
	public List<String> allKeys() {
		return cacheObjects
				.stream()
				.map(CacheObject::getKey)
				.collect(Collectors.toList());
	}

	@Override
	public List<String> getKeysFromMap(String key) {
		List<String> result = new ArrayList<>();
		CacheObject cacheObject = getCache(key);
		if (ObjectUtil.isNull(cacheObject))
			return result;

		Map<String, Object> map = cacheObject.getMap();
		if (ObjectUtil.isNull(map) || map.size() <= 0)
			return result;

		result = map.keySet().stream().collect(Collectors.toList());

		return result;
	}

	@Override
	public List<String> getKeysFromList(String key) {
		return null;
	}


	@Override
	public boolean setCache(String key, CacheObject value, long expiredSeconds) {
		CacheObject cacheObject = getCache(key);
		if (!ObjectUtil.isNull(cacheObject)) {
			if (!this.removeCache(key)) {
				throw new GlobalException("", "");
			}
		}
		cacheObject = value;
		cacheObject.setKey(key);
		cacheObject.setCreateTime(new Date());
		cacheObject.setExpiredSeconds(expiredSeconds);
		cacheObjects.add(cacheObject);
		return true;
	}

	@Override
	public boolean removeCache(String key) {
		for (int i = 0; i < cacheObjects.size(); i++) {
			CacheObject o = cacheObjects.get(i);
			if (o.getKey().equals(key) || o.getKey() == key) {
				cacheObjects.remove(o);
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean isExpire(String key) {
		CacheObject o = getCache(key);
		if (!ObjectUtil.isNull(o)) {
			return o.isExpire();
		}
		return true;
	}


}
