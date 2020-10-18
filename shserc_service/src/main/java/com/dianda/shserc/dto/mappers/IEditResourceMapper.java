package com.dianda.shserc.dto.mappers;

import com.dianda.shserc.dto.EditResourceDto;
import com.dianda.shserc.dto.EditUserDto;
import com.dianda.shserc.entity.Resource;
import com.dianda.shserc.util.mapper.BasicMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IEditResourceMapper extends BasicMapper<Resource, EditResourceDto> {
	
	 IEditResourceMapper INSTANCE= Mappers.getMapper ( IEditResourceMapper.class);
	
//	@Mappings( {
//			@Mapping(source="id",target="id"),
//	} )
	Resource mapFrom(EditResourceDto editUserDto );
}