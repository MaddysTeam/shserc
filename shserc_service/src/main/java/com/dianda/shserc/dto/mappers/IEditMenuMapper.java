package com.dianda.shserc.dto.mappers;

import com.dianda.shserc.dto.EditMenuDto;
import com.dianda.shserc.entity.Menu;
import com.dianda.common.util.mapper.BasicMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy= ReportingPolicy.IGNORE)
public interface IEditMenuMapper extends BasicMapper<Menu,EditMenuDto> {

	IEditMenuMapper INSTANCE = Mappers.getMapper(IEditMenuMapper.class);

	@Mappings({
			@Mapping(source = "id", target = "id"),
			@Mapping(source = "parentId", target = "parentId"),
			@Mapping(source = "level", target = "level"),
			@Mapping(source = "path", target = "path"),
			@Mapping(source = "title", target = "title"),
	})
	Menu mapFrom(EditMenuDto dto);
}

