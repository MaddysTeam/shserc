package com.dianda.shserc.dto.mappers;

import com.dianda.shserc.dto.EditRealDto;
import com.dianda.shserc.dto.EditRoleDto;
import com.dianda.shserc.entity.ResReal;
import com.dianda.shserc.entity.ResRole;
import com.dianda.shserc.util.mapper.BasicMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IEditRoleMapper extends BasicMapper<ResRole, EditRoleDto> {

	IEditRoleMapper INSTANCE = Mappers.getMapper(IEditRoleMapper.class);

	@Mappings({
			@Mapping(source = "id", target = "id"),
			@Mapping(source = "roleName", target = "roleName"),
			@Mapping(source="description",target="description"),
	})
	ResRole mapFrom(EditRoleDto dto);
}
