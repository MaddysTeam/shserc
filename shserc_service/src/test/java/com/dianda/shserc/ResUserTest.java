package com.dianda.shserc;

import com.dianda.shserc.bean.UserSelectParams;
import com.dianda.shserc.common.Constant;
import com.dianda.shserc.dto.EditUserDto;
import com.dianda.shserc.entity.ResUser;
import com.dianda.shserc.service.IResUserService;
import com.dianda.shserc.vo.ResUserVo;
import com.dianda.shserc.vo.ResUserVoList;
import org.apache.shiro.util.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest(classes = ServiceApplication.class)
@RunWith(SpringRunner.class)
public class ResUserTest {

	@Resource
	IResUserService service;

	int current = 1;
	int size = 2;
	String phrase = "jimmyPoor";
	long userId = 1;
	long companyId = 1;
	long zero = 0;

	@Test
	public void get_page_data_test() {
		pageUserDataWithEmptyPhraseTest();
		pageUserDataWithCompanyId();
		pageUserDataWithPhraseTest();
	}

	@Test
	public void delete_user_test() {
		ResUserVo vo = service.delete(userId);
		Assert.notNull(vo);
		Assert.isTrue(vo.isDeleted());
	}

	@Test
	public void edit_user_test() {
		EditUserDto userDto = new EditUserDto();
		userDto.setId(userId);
		userDto.setUserName("jimmyPoor2031");

		ResUserVo user = service.edit(userDto);
		Assert.notNull(user);
		Assert.isTrue(user.getId() == userId && user.getUserName() == userDto.getUserName());
	}


	private void pageUserDataWithEmptyPhraseTest() {
		UserSelectParams params = new UserSelectParams();
		params.setCompanyId(zero);
		params.setPhrase("");
		params.setCurrent(current);
		params.setSize(size);

		ResUserVoList userVo = service.find(params);
		Assert.notNull(userVo);
		Assert.isTrue(userVo.getSize() == size && userVo.getCurrent() == current);
	}


	private void pageUserDataWithPhraseTest() {
		UserSelectParams params = new UserSelectParams();
		params.setCompanyId(zero);
		params.setPhrase(phrase);
		params.setCurrent(current);
		params.setSize(size);

		ResUserVoList userVo = service.find(params);
		Assert.notNull(userVo);
		Assert.isTrue(userVo.getSize() == size && userVo.getCurrent() == current);
	}


	private void pageUserDataWithCompanyId() {
		UserSelectParams params = new UserSelectParams();
		params.setCompanyId(companyId);
		params.setPhrase(phrase);
		params.setCurrent(current);
		params.setSize(size);

		ResUserVoList userVo = service.find(params);
		Assert.notNull(userVo);
		Assert.isTrue(userVo.getSize() == size && userVo.getCurrent() == current);
	}

}
