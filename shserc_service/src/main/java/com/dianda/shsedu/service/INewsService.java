package com.dianda.shsedu.service;

import com.dianda.shsedu.bean.NewsSelectParams;
import com.dianda.shsedu.dto.EditNewsDto;
import com.dianda.shsedu.vo.NewsVo;
import com.dianda.shsedu.vo.NewsVoList;

import java.util.List;

public interface INewsService {

	NewsVoList findByPhrase(NewsSelectParams newsSelectParams);

	NewsVo findById( long id );

	boolean edit(EditNewsDto editNewsDto);

	boolean addViewCount(long id);
}
