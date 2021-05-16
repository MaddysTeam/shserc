package com.dianda.shsedu.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dianda.shsedu.bean.NewsSelectParams;
import com.dianda.shsedu.dto.EditNewsDto;
import com.dianda.shsedu.entity.News;
import com.dianda.shsedu.mapper.NewsMapper;
import com.dianda.shsedu.service.INewsService;
import com.dianda.shsedu.vo.NewsVo;
import com.dianda.shsedu.vo.NewsVoList;
import com.dianda.shsedu.vo.mappers.INewsVoMapper;
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
	public NewsVoList findByPhrase( NewsSelectParams newsSelectParams ) {
		return null;
	}
	
	@Override
	public NewsVo findById( long id ) {
		News news= mapper.selectById ( id );
		
		return INewsVoMapper.INSTANCE.mapTo ( news );
	}
	
	@Override
	public boolean edit( EditNewsDto editNewsDto ) {
		return false;
	}
	
	@Override
	public boolean addViewCount( long id ) {
		return false;
	}
	
}
