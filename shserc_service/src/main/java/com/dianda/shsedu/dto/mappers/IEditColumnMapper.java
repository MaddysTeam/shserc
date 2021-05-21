package com.dianda.shsedu.dto.mappers;

import com.dianda.common.util.mapper.BasicMapper;
import com.dianda.shsedu.dto.EditColumnDto;
import com.dianda.shsedu.entity.Columns;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

public interface IEditColumnMapper extends BasicMapper<Columns, EditColumnDto> {
	IEditColumnMapper INSTANCE = Mappers.getMapper(IEditColumnMapper.class);

	@Mappings({
			@Mapping(target = "id", source = "newsId"),
			@Mapping(target = "title", source = "title"),
			@Mapping(target = "isMenu", source = "isMenu"),
			@Mapping(target = "parentId", source = "parentId"),
	})
	Columns mapFrom(EditColumnDto source);
}
