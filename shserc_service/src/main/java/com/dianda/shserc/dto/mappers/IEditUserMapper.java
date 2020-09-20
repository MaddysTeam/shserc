package com.dianda.shserc.dto.mappers;

import com.dianda.shserc.dto.EditUserDto;
import com.dianda.shserc.entity.ResUser;
import com.dianda.shserc.util.mapper.BasicMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IEditUserMapper extends BasicMapper<ResUser, EditUserDto> {
	
	 IEditUserMapper INSTANCE= Mappers.getMapper ( IEditUserMapper.class);
	
	@Mappings( {
			@Mapping(source="id",target="id"),
			@Mapping( source="userName", target="userName" )
	} )
	ResUser mapFrom(EditUserDto editUserDto);
	
}