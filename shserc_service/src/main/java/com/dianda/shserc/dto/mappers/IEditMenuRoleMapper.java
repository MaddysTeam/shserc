package com.dianda.shserc.dto.mappers;

import com.dianda.shserc.dto.EditMenuRoleDto;
import com.dianda.shserc.entity.MenuRole;
import com.dianda.common.util.mapper.BasicMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy= ReportingPolicy.IGNORE)
public interface IEditMenuRoleMapper extends BasicMapper<MenuRole, EditMenuRoleDto> {
	
	IEditMenuRoleMapper INSTANCE= Mappers.getMapper ( IEditMenuRoleMapper.class);
	
//	@Mappings( {
//			@Mapping(source="menuId",target="menuId")
//	} )
	MenuRole mapFrom( EditMenuRoleDto editUserDto );
	
}