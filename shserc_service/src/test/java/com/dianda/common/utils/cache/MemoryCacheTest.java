package com.dianda.common.utils.cache;

import com.dianda.ServiceApplication;
import com.dianda.common.util.cache.CacheObject;
import com.dianda.common.util.cache.manager.MemoryCacheManager;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = ServiceApplication.class)
//@RunWith(SpringRunner.class)
public class MemoryCacheTest {

	MemoryCacheManager manager = new MemoryCacheManager();
	String key = "hollow";
	long expireSeconds = 1; // 0.001s
	long notExpireSeconds= 100000000;

	@Test
	public void SetCacheAndExpireTest() {
		CacheObject o1 = MockCacheDataFactory.mockSimpleObject();
		manager.setCache(key, o1, expireSeconds);
		CacheObject o2 = manager.getCache(key);

		Assert.assertNotEquals(o1, o2);
		Assert.assertTrue(o1.isExpire());
	}

	@Test
	public void SetCorrectCacheTest(){
		CacheObject o1 = MockCacheDataFactory.mockSimpleObject();
		manager.setCache(key, o1, notExpireSeconds);
		CacheObject o2 = manager.getCache(key);

		Assert.assertEquals(o1, o2);
		Assert.assertTrue(!o1.isExpire());
	}

}

