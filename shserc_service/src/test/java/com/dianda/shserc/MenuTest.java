package com.dianda.shserc;

import com.dianda.ServiceApplication;
import com.dianda.shserc.service.IMenuService;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest(classes = ServiceApplication.class)
public class MenuTest {

	@Resource
	IMenuService service;

	@Test
	public void search_menu_list_test(){

	}

	@Test
	public void edit_menu_test(){

	}

	@Test
	public void edit_menu_role_test(){

	}

}
