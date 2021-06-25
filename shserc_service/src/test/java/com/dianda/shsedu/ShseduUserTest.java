package com.dianda.shsedu;

import com.dianda.ServiceApplication;
import com.dianda.common.util.basic.DateTimeUtil;
import com.dianda.shsedu.dto.EditUserDto;
import com.dianda.shsedu.service.IUserService;
import org.apache.shiro.util.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest(classes = ServiceApplication.class)
@RunWith(SpringRunner.class)
public class ShseduUserTest {

	@Resource
	IUserService userService;

	int current = 1;
	int size = 2;
	String userName = "jimmyPoor";
	String password= "#Bugsfor123";
	long userId = 1;
	long companyId = 1;
	long zero = 0;

	@Test
	public void edit_user_test(){
		EditUserDto editUserDto=new EditUserDto();
		editUserDto.setUserName(userName);
		editUserDto.setPassword(password);
		editUserDto.setOperateDate(DateTimeUtil.now());
		boolean result=userService.edit(editUserDto);

		Assert.isTrue ( result );
	}


}
