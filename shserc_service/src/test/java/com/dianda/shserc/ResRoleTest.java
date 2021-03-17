package com.dianda.shserc;

import com.dianda.shserc.bean.RoleSelectParams;
import com.dianda.shserc.dto.EditRoleDto;
import com.dianda.shserc.service.IResRoleService;
import com.dianda.shserc.vo.ResRoleVoList;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest( classes = ServiceApplication.class )
@RunWith( SpringRunner.class )
public class ResRoleTest {
	
	@Resource
	IResRoleService service;
	
	int current = 1;
	int size = 2;
	String roleName = "ResourceAdmin";
	String description = "resource admin in charge of resource components";
	long resourceAdminRoleId = 2, operatorId= 1;
	
	@Test
	public void get_page_data_test( ) {
		pageRoleDataWithEmptyPhraseTest();
	}
	
	@Test
	public void add_role_test( ) {
		EditRoleDto editRoleDto = new EditRoleDto ( );
		editRoleDto.setRoleName ( roleName );
		editRoleDto.setDescription ( description );
		editRoleDto.setOperatorId ( operatorId );
		
		boolean result = service.edit ( editRoleDto );
		Assert.assertTrue ( result );
	}
	
	@Test
	public void edit_role_test( ) {
		EditRoleDto editRoleDto = new EditRoleDto ( );
		editRoleDto.setId ( resourceAdminRoleId );
		editRoleDto.setRoleName ( roleName );
		editRoleDto.setDescription ( description );
		editRoleDto.setOperatorId ( operatorId );
		
		boolean result = service.edit ( editRoleDto );
		Assert.assertTrue ( result );
	}
	
	
	private void pageRoleDataWithEmptyPhraseTest() {
		RoleSelectParams params = new RoleSelectParams();
		params.setSearchPhrase (""  );
		params.setCurrent(current);
		params.setSize(size);
		
		ResRoleVoList roleVoList = service.find(params);
		org.apache.shiro.util.Assert.notNull(roleVoList);
		org.apache.shiro.util.Assert.isTrue(roleVoList.getSize() == size && roleVoList.getCurrent() == current);
	}
	
	
}
