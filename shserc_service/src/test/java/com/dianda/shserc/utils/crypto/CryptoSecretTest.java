package com.dianda.shserc.utils.crypto;

import com.dianda.shserc.util.crypto.CryptoSecret;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CryptoSecretTest {

		String base64Word="+ZQi5AOeh910UNvD5jgBDw==";
		String sourceWord="#Bugsfor123";
	
		@Test
		public void deEncrypt_test(){
			String originalWord=CryptoSecret.desEncrypt ( base64Word );
			Assert.assertTrue ( originalWord.equals ( sourceWord ) );
		}
}
