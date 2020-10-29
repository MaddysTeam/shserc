package com.dianda.shserc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dianda.shserc.entity.Dictionary;
import com.dianda.shserc.entity.ResUser;
import com.dianda.shserc.mapper.DictionaryMapper;
import com.dianda.shserc.service.IDictionaryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dianda.shserc.util.cache.dictionary.DictionaryCache;
import com.dianda.shserc.vo.DictionaryVo;
import com.dianda.shserc.vo.mappers.IDictionaryVoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.util.ArrayList;
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
	
	@Autowired
	DictionaryCache dictionaryCache;
	
	@Override
	public DictionaryVo findAll() {
		QueryWrapper<Dictionary> wrapper = new QueryWrapper<>();
		List<Dictionary> list = mapper.selectList(wrapper);
		List<DictionaryVo> voList= new ArrayList<>();
		IDictionaryVoMapper mapper= IDictionaryVoMapper.INSTANCE;
		for (Dictionary item : list) {
			Dictionary.dictTranslate (item,dictionaryCache);
		    DictionaryVo vo=mapper.mapFrom(item);
			voList.add(vo);
		}

		DictionaryVo vo = new DictionaryVo();
		vo.setListData(voList);

		return vo;
	}
}
