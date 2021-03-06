package com.dianda.common.utils.cache;

import com.dianda.ServiceApplication;
import com.dianda.common.util.basic.ObjectUtil;
import com.dianda.common.util.cache.CacheObject;
import com.dianda.common.util.cache.dictionary.DictionaryCache;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest( classes = ServiceApplication.class )
@RunWith( SpringRunner.class)
public class DataDictionaryCacheTest {
	
	@Autowired
	DictionaryCache cache;
	
	long expireSeconds = 1;
	long notExpireSeconds = 1000000;
	
	@Before
	public void setUp( ) throws Exception {
		cache.setCacheFromService ( notExpireSeconds );
	}
	
	@Test
	public void setCacheWithExpireTest( ) {
		cache.setCacheFromService ( expireSeconds );
		CacheObject o = cache.getCache ( );
		
		Assert.assertEquals ( null , o );
	}
	
	@Test
	public void setCacheWithNotExpireTest( ) {
		CacheObject o = cache.getCache ( );
		
		Assert.assertNotEquals ( null , o );
		Assert.assertTrue ( o.getMap ( ).size ( ) > 0 );
	}
	
	@Test
	public void translateTest( ) {
		String gender = cache.translate ( "gender" , "1" ,"default");
		String gender2 = cache.translate ( "gender" , "0" ,"default");
		String gender3 = cache.translate ( "gender" , "2", "default" );
		
		Assert.assertTrue ( gender.equals ( "男" ) );
		Assert.assertTrue ( gender2.equals ( "女" ) );
		Assert.assertTrue ( ObjectUtil.isNull ( gender3 ) );
	}
	
}
