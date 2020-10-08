package com.dianda.shserc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dianda.shserc.entity.Dictionary;
import com.dianda.shserc.entity.ResUser;
import com.dianda.shserc.mapper.DictionaryMapper;
import com.dianda.shserc.service.IDictionaryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dianda.shserc.vo.DictionaryVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 字典实现类
 *
 * @author huachao
 * @since 2020-10-05
 */
@Service
public class DictionaryServiceImpl extends ServiceImpl<DictionaryMapper, Dictionary> implements IDictionaryService {
	@Resource
	DictionaryMapper mapper;
	
	@Override
	public DictionaryVo findAll( ) {
		QueryWrapper<Dictionary> wrapper = new QueryWrapper<> ( );
		List<Dictionary> list = mapper.selectList ( wrapper );
		DictionaryVo vo= new DictionaryVo ();
		vo.setListData ( list );
		
		return vo;
	}
}
