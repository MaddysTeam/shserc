package com.dianda.shserc.dto.mappers;

import com.dianda.shserc.dto.EditUserRoleDto;
import com.dianda.shserc.entity.ResUserRole;
import com.dianda.shserc.util.mapper.BasicMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy= ReportingPolicy.IGNORE)
public interface IEditUserRoleMapper extends BasicMapper<ResUserRole, EditUserRoleDto> {
	
	 IEditUserRoleMapper INSTANCE= Mappers.getMapper ( IEditUserRoleMapper.class);
	
	@Mappings( {
			@Mapping(source="userId",target="userId"),
			@Mapping( source="roleId", target="roleId" )
	} )
	ResUserRole mapFrom(EditUserRoleDto editUserDto);
	
}