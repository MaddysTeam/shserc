package com.dianda.auth;

import com.dianda.auth.service.IResUserService;
import com.dianda.auth.vo.ResUserVo;
import org.apache.shiro.util.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest( classes = AuthApplication.class )
@RunWith( SpringRunner.class )
public class ResUserTest {
	
	@Resource
	IResUserService service;
	
	int current = 1;
	int size = 2;
	
	@Test
	public void PageUserDataWithEmptyPhraseTest( ) {
		ResUserVo userVo = service.findByPhrase ( "" , current , size );
		Assert.notNull ( userVo);
		Assert.isTrue ( userVo.getSize ()==size  && userVo.getCurrent ()==current);
	}
	
	@Test
	public void PageUserDataWithPhraseTest( ) {
		ResUserVo userVo = service.findByPhrase ( "jimmyPoor" , current , size );
		Assert.notNull ( userVo);
		Assert.isTrue ( userVo.getSize ()==size  && userVo.getCurrent ()==current);
	}
	
}
