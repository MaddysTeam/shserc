package com.dianda.shsedu.dto.mappers;

import com.dianda.common.util.mapper.BasicMapper;
import com.dianda.shsedu.dto.EditPermissionDto;
import com.dianda.shsedu.dto.EditRoleDto;
import com.dianda.shsedu.entity.Permission;
import com.dianda.shsedu.entity.Roles;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

public interface IEditPermissionMapper extends BasicMapper<Permission, EditPermissionDto> {
	IEditPermissionMapper INSTANCE = Mappers.getMapper(IEditPermissionMapper.class);

	@Mappings({
			@Mapping(target = "id", source = "id"),
     		@Mapping(target = "sort", source = "sort"),
			@Mapping(target = "parentId", source = "parentId"),
			@Mapping(target = "name", source = "name"),
			@Mapping(target = "description", source = "description"),
			@Mapping(target = "url", source = "url"),
			@Mapping(target = "isMenu", source = "isMenu"),
	})
	EditPermissionDto mapFrom(EditRoleDto Permission);
}
