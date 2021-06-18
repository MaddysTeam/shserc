package com.dianda.shsedu.dto.mappers;

import com.dianda.common.util.mapper.BasicMapper;
import com.dianda.shsedu.dto.EditNewsDto;
import com.dianda.shsedu.dto.EditUserDto;
import com.dianda.shsedu.entity.News;
import com.dianda.shsedu.entity.ShseduUser;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

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
