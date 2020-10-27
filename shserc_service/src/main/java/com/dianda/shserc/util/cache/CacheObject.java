package com.dianda.shserc.util.cache;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class CacheObject {
	private String key;
	private Object value;
	private long expiredSeconds;
	private Map<String, Object> map;
	private Map<String, Map<String, Object>> mapMap;
	private Map<String, List<Object>> mapList;
	private List<Map<String, Object>> listMap;
}

class DBCacheObject extends CacheObject {
	private String dbType;
	private String dbName;
	private String host;
	private String port;
	private String[] tableNames;


}

interface ICacheManager<T> {
	T getCache(String key);

	void setCache(String key, Object value, long expiredSeconds);

	void setCache(String key, Map<String, Object> value, long expiredSeconds);

	void setCache(String key, List<Object> value, long expiredSeconds);
}

interface IDbCacheManager extends ICacheManager<DBCacheObject> {
	void initial(String host, String port, String[] tables);

	DBCacheObject getCacheFromTable(String table);
}


class DbCacheManager implements IDbCacheManager {

	static List<DBCacheObject> cacheObjects = new ArrayList<>();
	DBCacheObject current;

	@Override
	public DBCacheObject getCache(String key) {
		for (DBCacheObject o : cacheObjects) {
			if (o.getKey().equals(key) || o.getKey() == key) {
				return o;
			}
		}
		return new DBCacheObject();
	}

	@Override
	public void setCache(String key, Object value, long expiredSeconds) {
		DBCacheObject cacheObject = current;
		cacheObject.setKey(key);
		cacheObject.setValue(value);

		cacheObjects.add(cacheObject);
	}

	@Override
	public void setCache(String key, Map<String, Object> value, long expiredSeconds) {

	}

	@Override
	public void setCache(String key, List<Object> value, long expiredSeconds) {

	}

	@Override
	public void initial(String host, String port, String[] tables) {
		this.current = null;
	}

	@Override
	public DBCacheObject getCacheFromTable(String table) {
		return new DBCacheObject();
	}


	class DBCacheObjectFactory {


	}


}

