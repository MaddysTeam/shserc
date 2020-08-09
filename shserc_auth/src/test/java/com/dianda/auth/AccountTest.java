package com.dianda.auth;

import com.dianda.auth.dto.LoginDto;
import com.dianda.auth.util.shiro.jwt.JwtOperation;
import com.dianda.auth.util.shiro.jwt.JwtSettings;
import org.apache.shiro.util.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;

@SpringBootTest
@RunWith ( SpringRunner.class )
@EnableConfigurationProperties({ JwtSettings.class})
public class AccountTest {
 
	LoginDto loginDTO=new LoginDto ();
	
	public AccountTest(){
		loginDTO.setUserName("jimmyPoor");
		loginDTO.setPassword ( "123456" );
		
	}
	
	@Test
	 public void VerifyTokenTest() throws UnsupportedEncodingException {
		String token = JwtOperation.Sign(loginDTO.getUserName (),System.currentTimeMillis ());
		boolean verifyResult=JwtOperation.verifyToken(token);
		Assert.isTrue ( verifyResult);
	 }
}
