package com.dianda.shserc.utils.cache;

import com.dianda.shserc.ServiceApplication;
import com.dianda.shserc.util.cache.CacheObject;
import com.dianda.shserc.util.cache.manager.ICacheManager;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest( classes = ServiceApplication.class )
@RunWith(SpringRunner.class)
public class RedisCacheTest {
	
	@Resource(name="redisCache")
	ICacheManager cacheManager;
	
	String key = "hollow";
	long expireSeconds = 1; // 0.001s
	long notExpireSeconds = 0;
	
	@Test
	public void SetCacheAndExpireTest( ) {
		CacheObject o1 = MockCacheDataFactory.mockSimpleObject ( );
		cacheManager.setCache ( key , o1 , 0 );
		CacheObject o2 = cacheManager.getCache ( key );
		
		Assert.assertEquals ( o1,o2);
	}
}
