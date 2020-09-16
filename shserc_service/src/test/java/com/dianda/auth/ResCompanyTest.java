package com.dianda.auth;

import com.dianda.auth.service.IResCompanyService;
import com.dianda.auth.vo.ResCompanyVo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 *  @title ResCompanyTest
 *  @Description 描述
 *  @author huachao
 *  @Date 2020/9/12 22:39
 *  @Copyright 2019-2020
 */

@SpringBootTest( classes = AuthApplication.class )
@RunWith( SpringRunner.class )
public class ResCompanyTest {
	
	@Resource
	IResCompanyService service;
	
	long areaId = 3; // 虹口区
	
	@Test
	public void companyDataRecursiveMappingTest( ) {
		ResCompanyVo result = service.find ( );
		Assert.assertTrue ( result != null );
		// test for area
		Assert.assertTrue ( result.getChildren ( ) != null && result.getChildren ( ).size ( ) > 0 );
		// test for school
		Assert.assertTrue ( result.getChildren ( ).get ( areaId ).getChildren ( ).size ( ) > 0 );
	}
	
}
