package com.dianda.auth;

import com.dianda.auth.common.Constant;
import com.dianda.auth.dto.EditUserDto;
import com.dianda.auth.entity.ResUser;
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
	String phrase = "jimmyPoor";
	String userId = "b2f1f721-044f-464f-bf7c-2d99e97362f1";
	
	@Test
	public void pageUserDataWithEmptyPhraseTest( ) {
		ResUserVo userVo = service.findByPhrase ( "" , current , size );
		Assert.notNull ( userVo );
		Assert.isTrue ( userVo.getSize ( ) == size && userVo.getCurrent ( ) == current );
	}
	
	@Test
	public void pageUserDataWithPhraseTest( ) {
		ResUserVo userVo = service.findByPhrase ( phrase , current , size );
		Assert.notNull ( userVo );
		Assert.isTrue ( userVo.getSize ( ) == size && userVo.getCurrent ( ) == current );
	}
	
	@Test
	public void addUserTest( ) {
	
	}
	
	@Test
	public void deleteUserWithExistIdTest( ) {
		ResUser user = service.delete ( userId );
		Assert.notNull ( user );
		Assert.isTrue ( user.getIsDeleted ( ) == Constant.Status.DELETED );
	}
	
	@Test
	public void updateUserTest( )  {
		EditUserDto userDto = new EditUserDto ( );
		userDto.setId ( userId );
		userDto.setUserName ( "jimmyPoor2031" );
		
		ResUser user = service.edit ( userDto );
		Assert.notNull ( user );
		Assert.isTrue ( user.getId ()==userId && user.getUserName ()==userDto.getUserName ());
	}
	
}
