package com.dianda.shsedu.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dianda.common.util.basic.ObjectUtil;
import com.dianda.common.util.cache.dictionary.DictionaryCache;
import com.dianda.common.validators.NotNull;
import com.dianda.shsedu.bean.NewsSelectParams;
import com.dianda.shsedu.dto.EditNewsDto;
import com.dianda.shsedu.dto.mappers.IEditNewsMapper;
import com.dianda.shsedu.entity.News;
import com.dianda.shsedu.mapper.NewsMapper;
import com.dianda.shsedu.service.INewsService;
import com.dianda.shsedu.vo.NewsVo;
import com.dianda.shsedu.vo.NewsVoList;
import com.dianda.shsedu.vo.mappers.INewsVoMapper;
import com.dianda.common.common.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@DS("shsedu")
@Service("ShseduNewsService")
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements INewsService {

	@Resource
	NewsMapper mapper;

	DictionaryCache cache;

	@Autowired
	public NewsServiceImpl(DictionaryCache cache) {
		this.cache = cache;
		if (ObjectUtil.isNull(cache.getCache())){
			this.cache.setCacheFromService(0);
		}
	}

	@Override
	public NewsVoList findByPhrase(NewsSelectParams params) {
		QueryWrapper<News> wrapper = new QueryWrapper<>();
		if (params.typeId > 0) {
			wrapper = wrapper.eq("typeId", params.typeId);
		}
		if (params.columnId > 0) {
			wrapper = wrapper.eq("columnId", params.columnId);
		}
		if (params.statusId > 0) {
			wrapper = wrapper.eq("statusId", params.statusId);
		}

		Map<String, String> orderPhrases = params.getOrderPhrases();

		if (!ObjectUtil.isNull(orderPhrases)) {
			for (Map.Entry<String, String> entry : orderPhrases.entrySet()) {
				String orderPhrase = params.translateOrderPhrase(entry.getKey());
				String direction = entry.getValue();
				if (direction.equals(Constant.OrderDirection.ASC)) {
					wrapper = wrapper.orderByAsc(orderPhrase);
				} else if (direction.equals(Constant.OrderDirection.DESC)) {
					wrapper = wrapper.orderByDesc(orderPhrase);
				}
			}
		} else {
			wrapper = wrapper.orderByDesc("id");
		}

		// get paged list data

		int current = params.getCurrent();
		int size = params.getSize();
		IPage<News> page = new Page<>(current, size);
		page = mapper.selectNews(page, wrapper);
		List<News> newsList = page.getRecords();
		List<NewsVo> newsVoList = new ArrayList<>();

		// translate

		for (News news : newsList) {
			News.dictTranslate(news, cache); // 翻译字典
			NewsVo vo = INewsVoMapper.INSTANCE.mapFrom(news);
			newsVoList.add(vo);
		}

		// get vo list data

		NewsVoList voList = new NewsVoList();
		voList.setCurrent(current);
		voList.setSize(size);
		voList.setTotal(page.getTotal());

		if (!ObjectUtil.isNull(newsVoList) && newsVoList.size() > 0)
			voList.setListData(newsVoList);

		return voList;
	}

	@Override
	public NewsVo findById(long id) {
		News news = mapper.selectById(id);
		return INewsVoMapper.INSTANCE.mapFrom(news);
	}

	@Override
	public boolean edit(@Valid @NotNull EditNewsDto editNewsDto) {
//		News news = IEditNewsMapper.INSTANCE.mapFrom(editNewsDto);
//		NewsVo newsVo = findById(news.getId());
//		news.setAddUser(editNewsDto.getOperatorId());
//		news.setAddTime(editNewsDto.getOperateDate());
//		if (news.isNewOne() && ObjectUtil.isNull(newsVo)) {
//			return mapper.insert(news) > 0;
//		} else {
//			news.setUpdateTime(editNewsDto.getOperateDate());
//			news.setUpdateUser(editNewsDto.getOperatorId());
//
//			return mapper.updateById(news) >= 0;
//		}
		return false;
	}

	@Override
	public boolean addViewCount(long id) {
		News news = mapper.selectById(id);
		if (ObjectUtil.isNull(news)) {
			return false;
		}

		news.addViewCount();
		return mapper.updateById(news) > 0;
	}

}
