package com.dianda.shsedu.dto.mappers;

import com.dianda.common.util.mapper.BasicMapper;
import com.dianda.shsedu.dto.EditColumnDto;
import com.dianda.shsedu.entity.Columns;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IEditColumnMapper extends BasicMapper<Columns, EditColumnDto> {
	IEditColumnMapper INSTANCE = Mappers.getMapper(IEditColumnMapper.class);

	@Mappings({
			@Mapping(target = "id", source = "id"),
			@Mapping(target = "title", source = "title"),
			@Mapping(target = "menu", source = "menu"),
			@Mapping(target = "parentId", source = "parentId"),
			@Mapping(target = "orderNo", source = "orderNo"),
	})
	Columns mapFrom(EditColumnDto source);
}
