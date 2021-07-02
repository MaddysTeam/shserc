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
			@Mapping(target = "title", source = "title"),
			@Mapping(target = "subTitle", source = "subTitle"),
			@Mapping(target = "keyword", source = "keyword"),
			@Mapping(target = "content", source = "content"),
			@Mapping(target = "columnId", source = "columnId"),
			@Mapping(target = "typeId", source = "typeId"),
			@Mapping(target = "type", source = "type"),
			@Mapping(target = "status", source = "status"),
			@Mapping(target = "districtId", source = "districtId"),
			@Mapping(target = "district", source = "district"),
			@Mapping(target = "coverPath", source = "coverPath"),
			@Mapping(target = "visitCount", source = "visitCount")
	})
	NewsVo mapFrom(News source);
}
