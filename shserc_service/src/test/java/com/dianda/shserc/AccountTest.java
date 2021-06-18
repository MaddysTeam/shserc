package com.dianda.shserc;

import com.dianda.ServiceApplication;
import com.dianda.common.dto.LoginDto;
import com.dianda.common.service.IAccountService;
import com.dianda.common.util.basic.ObjectUtil;
import com.dianda.common.security.shiro.jwt.JwtOperation;
import com.dianda.common.security.shiro.jwt.JwtSettings;
import org.apache.shiro.util.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import java.io.UnsupportedEncodingException;

//@ActiveProfiles( "local" )
@SpringBootTest( classes = ServiceApplication.class )
@RunWith( SpringRunner.class )
@EnableConfigurationProperties( { JwtSettings.class } )
public class AccountTest {
	
	@Autowired
	WebApplicationContext wac;
	
	@Autowired
	IAccountService accountService;
	
	LoginDto loginDTO = new LoginDto ( );

//	@Before
//	public void setupMock(){
//		SecurityUtils.setSecurityManager( null );
//	}
	
	public AccountTest( ) {
		loginDTO.setUserName ( "jimmyPoor" );
		loginDTO.setPassword ( "123456" );
	}
	
	@Test
	public void verify_token_test( ) throws UnsupportedEncodingException {
		String token = JwtOperation.Sign ( loginDTO.getUserName ( ) , System.currentTimeMillis ( ) );
		boolean verifyResult = JwtOperation.verifyToken ( token );
		Assert.isTrue ( verifyResult );
	}
	
	@Test
	public void login_test( ) {
		boolean result = accountService.login ( loginDTO );
		Assert.isTrue ( result && ! ObjectUtil.isNull ( loginDTO.getToken ( ) ) );
	}
	
	@Test
	public void change_password_test(){
	
	}
	
	@Test
	public void forgetPassword_test(){
	
	}
	
}
