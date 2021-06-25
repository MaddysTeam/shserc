package com.dianda.shsedu.dto.mappers;

import com.dianda.common.util.mapper.BasicMapper;
import com.dianda.shsedu.dto.EditUserDto;
import com.dianda.shsedu.entity.ShseduUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IEditUserMapper extends BasicMapper<ShseduUser, EditUserDto> {
	IEditUserMapper INSTANCE = Mappers.getMapper(IEditUserMapper.class);

	@Mappings({
			@Mapping(target = "id", source = "id"),
			@Mapping(target = "userName", source = "userName"),
			@Mapping(target = "realName", source = "realName"),
			@Mapping(target = "roleId", source = "roleId"),
			@Mapping(target = "districtId", source = "districtId"),
			@Mapping(target = "email", source = "email"),
			@Mapping(target = "mobile", source = "mobile"),
			@Mapping(target = "permissionIds", source = "permissionIds"),
	})
	ShseduUser mapFrom(EditUserDto source);
}
