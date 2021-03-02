package com.dianda.shserc;

import com.dianda.shserc.dto.LoginDto;
import com.dianda.shserc.service.IAccountService;
import com.dianda.shserc.util.shiro.jwt.JwtOperation;
import com.dianda.shserc.util.shiro.jwt.JwtSettings;
import org.apache.shiro.util.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;

@ActiveProfiles("local")
@SpringBootTest(classes = ServiceApplication.class)
@RunWith ( SpringRunner.class )
@EnableConfigurationProperties({ JwtSettings.class})
public class AccountTest {
	
	@Autowired
	WebApplicationContext wac;
	
	@Resource
	SecurityManager securityManager;
	
	@Autowired
	IAccountService accountService;
	
	LoginDto loginDTO=new LoginDto ();
	
//	@Before
//	public void setupMock(){
//		SecurityUtils.setSecurityManager( null );
//	}
	
	public AccountTest(){
		loginDTO.setUserName("jimmyPoor");
		loginDTO.setPassword ( "123456" );
	}
	
	@Test
	 public void verify_token_test() throws UnsupportedEncodingException {
		String token = JwtOperation.Sign(loginDTO.getUserName (),System.currentTimeMillis ());
		boolean verifyResult=JwtOperation.verifyToken(token);
		Assert.isTrue ( verifyResult);
	 }
	 
	 @Test
	public void login_test(){
		 LoginDto result= accountService.login ( loginDTO );
		 Assert.notNull ( result.getToken () );
	 }
	 
}
