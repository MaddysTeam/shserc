package com.dianda.shsedu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dianda.shsedu.bean.NewsSelectParams;
import com.dianda.shsedu.dto.AuditNewsDto;
import com.dianda.shsedu.dto.EditNewsDto;
import com.dianda.shsedu.entity.News;
import com.dianda.shsedu.vo.NewsVo;
import com.dianda.shsedu.vo.NewsVoList;

import java.util.List;

public interface INewsService extends IService<News> {

	NewsVoList findByPhrase(NewsSelectParams newsSelectParams);

	NewsVo findById( long id );

	boolean edit(EditNewsDto editNewsDto);

	boolean audit(AuditNewsDto auditNewsDto);

	boolean addViewCount(long id);
}
