package com.dianda.shsedu;

import com.dianda.ServiceApplication;
import com.dianda.common.dto.LoginDto;
import com.dianda.common.security.shiro.jwt.JwtOperation;
import com.dianda.common.security.shiro.jwt.JwtSettings;
import com.dianda.common.service.IAccountService;
import com.dianda.common.util.basic.ObjectUtil;
import org.apache.shiro.util.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = ServiceApplication.class)
@RunWith( SpringRunner.class )
@EnableConfigurationProperties( { JwtSettings.class } )

public class AccountTest {

	@Qualifier("ShseduAccountService")
	@Autowired
	IAccountService accountService;

	LoginDto loginDto=new LoginDto();

	public AccountTest(){
		loginDto.setUserName("JimmyPoor");
		loginDto.setPassword ( "#Bugsfor123" );
	}

	@Test
	public void login_test( ) {
		boolean result = accountService.login ( loginDto );
		Assert.isTrue ( result && ! ObjectUtil.isNull ( loginDto.getToken ( ) ) );
	}

}
