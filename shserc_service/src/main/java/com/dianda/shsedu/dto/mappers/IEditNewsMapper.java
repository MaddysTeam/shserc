package com.dianda.shsedu.dto.mappers;

import com.dianda.common.util.mapper.BasicMapper;
import com.dianda.shsedu.dto.AuditNewsDto;
import com.dianda.shsedu.dto.EditNewsDto;
import com.dianda.shsedu.entity.News;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

public interface IEditNewsMapper extends BasicMapper<News, EditNewsDto> {
	IEditNewsMapper INSTANCE = Mappers.getMapper(IEditNewsMapper.class);

	@Mappings({
			@Mapping(target = "id", source = "id"),
			@Mapping(target = "title", source = "title"),
			@Mapping(target = "key", source = "t"),
			@Mapping(target = "typeId", source = "typeId"),
			@Mapping(target = "statusId", source = "statusId"),
			@Mapping(target = "columnId", source = "columnId"),
			@Mapping(target = "districtId", source = "districtId"),
			@Mapping(target = "coverPath", source = "coverPath"),
	})
	News mapFrom(EditNewsDto source);
}


