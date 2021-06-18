package com.dianda.shsedu.vo.mappers;

import com.dianda.common.util.mapper.BasicMapper;
import com.dianda.shsedu.entity.News;
import com.dianda.shsedu.entity.Roles;
import com.dianda.shsedu.vo.NewsVo;
import com.dianda.shsedu.vo.RoleVo;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

public interface IRoleVoMapper extends BasicMapper<RoleVo, Roles> {
	IRoleVoMapper INSTANCE = Mappers.getMapper(IRoleVoMapper.class);

	@Mappings({
			@Mapping(target = "id", source = "id"),
			@Mapping(target = "roleName", source = "roleName"),
			@Mapping(target = "description", source = "description"),
			@Mapping(target = "permissionIds", source = "permissionIds"),
	})
	RoleVo mapFrom(Roles source);
}
