package com.dianda.shsedu.vo.mappers;

import com.dianda.common.util.mapper.BasicMapper;
import com.dianda.shsedu.entity.News;
import com.dianda.shsedu.vo.NewsVo;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

public interface INewsVoMapper extends BasicMapper<NewsVo, News> {
	INewsVoMapper INSTANCE = Mappers.getMapper(INewsVoMapper.class);

	@Mappings({
			@Mapping(target = "id", source = "id"),
//			@Mapping(target = "", source = ""),
//			@Mapping(target = "", source = "")
	})
	NewsVo mapFrom(News source);
}
