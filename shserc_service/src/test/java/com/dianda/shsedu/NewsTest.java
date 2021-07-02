package com.dianda.shsedu;

import com.dianda.ServiceApplication;
import com.dianda.common.util.basic.DateTimeUtil;
import com.dianda.shsedu.common.ShseduConstant;
import com.dianda.shsedu.dto.EditNewsDto;
import com.dianda.shsedu.service.INewsService;
import com.dianda.shsedu.vo.NewsVo;
import com.dianda.shsedu.vo.NewsVoList;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 *  @title ResCompanyTest
 *  @Description 描述
 *  @author huachao
 *  @Date 2020/9/12 22:39
 *  @Copyright 2019-2020
 */

@SpringBootTest( classes = ServiceApplication.class )
@RunWith( SpringRunner.class )
public class NewsTest {
	
	@Resource
	INewsService service;

	String title="新闻标题";
	String content="content here";
	long columnId=1000;
	long statusId= ShseduConstant.State.NEEDAUDIT;
	long typeId=ShseduConstant.NewsType.OTHERTYPES;
	long operatorId=1000;
	long districtId=1001;
	Date operateDate= DateTimeUtil.now();
	String keyword="keyword";
	
	@Test
	public void get_news_test( ) {
		NewsVo news  = service.findById (1 );
		Assert.assertTrue ( news != null );
	}
	
	
	@Test
	public void get_news_paged_data_test( ) {
		NewsVoList newsList  = service.findByPhrase (null);
		Assert.assertTrue ( newsList != null );
	}

	@Test
	public void edit_news_test(){
		EditNewsDto newsDto=new EditNewsDto();
		newsDto.setTitle(title);
		newsDto.setColumnId(columnId);
		newsDto.setStatusId(statusId);
		newsDto.setContent(content);
		newsDto.setTypeId(typeId);
		newsDto.setKeyword(keyword);
		newsDto.setOperatorId(operatorId);
		newsDto.setOperateDate(operateDate);

		boolean result= service.edit(newsDto);

		Assert.assertTrue(result);
	}

	@Test
	public void audit_news_test(){

	}

}
