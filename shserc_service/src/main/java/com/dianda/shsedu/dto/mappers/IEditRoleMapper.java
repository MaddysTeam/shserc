package com.dianda.shsedu.dto.mappers;

import com.dianda.common.util.mapper.BasicMapper;
import com.dianda.shsedu.entity.Roles;
import com.dianda.shsedu.dto.EditRoleDto;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

public interface IEditRoleMapper extends BasicMapper<Roles, EditRoleDto> {
	IEditRoleMapper INSTANCE = Mappers.getMapper(IEditRoleMapper.class);

	@Mappings({
			@Mapping(target = "id", source = "id"),
			@Mapping(target = "roleName", source = "roleName"),
			@Mapping(target = "description", source = "description"),
			@Mapping(target = "permissionIds", source = "permissionIds"),
	})
	Roles mapFrom(EditRoleDto source);
}
