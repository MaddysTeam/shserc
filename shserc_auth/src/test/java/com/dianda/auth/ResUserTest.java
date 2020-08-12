package com.dianda.auth;

import com.dianda.auth.service.IResUserService;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest(classes = AuthApplication.class)
@RunWith(SpringRunner.class)
public class ResUserTest {

	@Resource
	IResUserService service;

}
