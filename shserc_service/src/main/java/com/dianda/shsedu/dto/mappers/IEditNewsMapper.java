package com.dianda.shsedu.dto.mappers;

import com.dianda.common.util.mapper.BasicMapper;
import com.dianda.shsedu.dto.EditNewsDto;
import com.dianda.shsedu.entity.News;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

public interface IEditNewsMapper extends BasicMapper<News, EditNewsDto> {
	IEditNewsMapper INSTANCE = Mappers.getMapper(IEditNewsMapper.class);

	@Mappings({
			@Mapping(target = "id", source = "newsId"),
			@Mapping(target = "title", source = "title"),
			@Mapping(target = "typeId", source = "typeId"),
			@Mapping(target = "statusId", source = "statusId"),
	})
	News mapFrom(EditNewsDto source);
}
