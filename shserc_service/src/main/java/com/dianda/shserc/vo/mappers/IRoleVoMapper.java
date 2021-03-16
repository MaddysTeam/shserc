package com.dianda.shserc.vo.mappers;

import com.dianda.shserc.entity.ResRole;
import com.dianda.shserc.util.mapper.BasicMapper;
import com.dianda.shserc.vo.ResRoleVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IRoleVoMapper extends BasicMapper<ResRoleVo, ResRole> {

	IRoleVoMapper INSTANCE = Mappers.getMapper ( IRoleVoMapper.class );

	@Mappings({
			@Mapping(source = "id", target = "id"),
			@Mapping(source = "roleName", target = "roleName"),
			@Mapping(source="description",target="description"),
	})
	ResRoleVo mapFrom(ResRole resRole);
}
