package com.dianda.shserc;

import com.dianda.ServiceApplication;
import com.dianda.shserc.service.IResCompanyService;
import com.dianda.shserc.vo.ResCompanyVo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 *  @title ResCompanyTest
 *  @Description 描述
 *  @author huachao
 *  @Date 2020/9/12 22:39
 *  @Copyright 2019-2020
 */

@SpringBootTest( classes = ServiceApplication.class )
@RunWith( SpringRunner.class )
public class ResCompanyTest {
	
	@Resource
	IResCompanyService service;
	
	long areaId = 3; // 虹口区
	
	@Test
	public void get_company_data_test( ) {
		ResCompanyVo result = service.find ( );
		Assert.assertTrue ( result != null );
		// test for area
		Assert.assertTrue ( result.getChildren ( ) != null && result.getChildren ( ).size ( ) > 0 );
		// test for school
		//Assert.assertTrue ( result.getChildren ( ).get ( areaId ).getChildren ( ).size ( ) > 0 );
	}

	@Test
	public void edit_company_test(){

	}
	
}
