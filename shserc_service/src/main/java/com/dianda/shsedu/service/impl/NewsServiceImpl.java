package com.dianda.shsedu.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dianda.shsedu.entity.News;
import com.dianda.shsedu.mapper.NewsMapper;
import com.dianda.shsedu.service.INewsService;
import com.dianda.shserc.entity.ResReal;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@DS("shsedu")
@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements INewsService {
	
	@Resource
	NewsMapper mapper;
	
	@Override
	public List<News> findByPhrase( ) {
		IPage<News> page=new Page<> (  );
		page.setCurrent ( 0 );
		page.setSize ( 1 );
		
		QueryWrapper<News> queryWrapper = new QueryWrapper<> ( );
		
		List<News> newsList=mapper.selectNews (page,queryWrapper ).getRecords ();
		
		return newsList;
	}
	
	@Override
	public News findById( long id ) {
		return mapper.selectById ( id );
	}
	
}
