package com.dianda.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dianda.common.entity.Dictionary;
import com.dianda.common.mapper.DictionaryMapper;
import com.dianda.common.service.IDictionaryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dianda.common.util.cache.dictionary.DictionaryCache;
import com.dianda.common.vo.DictionaryVo;
import com.dianda.shserc.vo.mappers.IDictionaryVoMapper;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
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
	
	@Resource
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
