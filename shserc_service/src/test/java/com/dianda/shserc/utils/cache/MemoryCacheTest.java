package com.dianda.shserc.utils.cache;

import com.dianda.shserc.ServiceApplication;
import com.dianda.shserc.util.cache.CacheObject;
import com.dianda.shserc.util.cache.manager.MemoryCacheManager;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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

	static class MockCacheDataFactory {

		public static CacheObject mockSimpleObject() {
			CacheObject o = new CacheObject();
			return o;
		}

	}

}
