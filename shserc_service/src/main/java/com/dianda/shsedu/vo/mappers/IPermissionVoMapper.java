package com.dianda.shsedu.vo.mappers;

import com.dianda.common.util.mapper.BasicMapper;
import com.dianda.shsedu.entity.Permission;
import com.dianda.shsedu.entity.Roles;
import com.dianda.shsedu.vo.PermissionVo;
import com.dianda.shsedu.vo.RoleVo;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

public interface IPermissionVoMapper extends BasicMapper<RoleVo, Roles> {
	IPermissionVoMapper INSTANCE = Mappers.getMapper(IPermissionVoMapper.class);

	@Mappings({
			@Mapping(target = "id", source = "id"),
			@Mapping(target = "roleName", source = "roleName"),
			@Mapping(target = "description", source = "description"),
			@Mapping(target = "permissionIds", source = "permissionIds"),
	})
	PermissionVo mapFrom(Permission source);
}
