package com.dianda.shserc;

import com.dianda.shserc.dto.EditRealDto;
import com.dianda.shserc.entity.ResReal;
import com.dianda.shserc.service.IResRealService;
import com.dianda.shserc.util.basic.ObjectUtil;
import com.dianda.shserc.vo.ResRealVo;
import org.apache.shiro.util.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
@RunWith( SpringRunner.class )
public class ResRealTest {
	
	@Resource
	IResRealService service;
	
	long userId = 1;
	long zero = 0;
	String cardNo = "kv2002";
	String idCard = "310115198610191992";
	String realName = "华超";
	String password = "#Bugsfor123";
	EditRealDto model;
	ResRealVo vo;
	
	@Test
	public void add_real_service_with_correct_result_Test( ) {
		model = new EditRealDto ( );
		model.setCardNo ( cardNo );
		model.setCardPassword ( password );
		model.setRealName ( realName );
		model.setIdCard ( idCard );
		boolean result = service.edit ( model );
		Assert.isTrue ( result );
		
		model.setRealName ( "张三" );
		result = service.edit ( model );
		Assert.isTrue ( result );
		
		vo = service.findByIdCard ( idCard );
		Assert.isTrue ( model.getIdCard ( ) .equals (  vo.getIdCard ( )) );
	}
	
}
