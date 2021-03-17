package com.dianda.shserc.dto.mappers;

import com.dianda.shserc.dto.EditMenuDto;
import com.dianda.shserc.entity.Menu;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

public interface IEditMenuMapper {

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

