package com.dianda.shsedu.vo.mappers;

import com.dianda.common.util.mapper.BasicMapper;
import com.dianda.shsedu.entity.ShseduUser;
import com.dianda.shsedu.vo.UserVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserVoMapper extends BasicMapper<UserVo, ShseduUser> {
	IUserVoMapper INSTANCE = Mappers.getMapper(IUserVoMapper.class);

	@Mappings({
			@Mapping(target = "id", source = "id"),
			@Mapping(target = "userName", source = "userName"),
			@Mapping(target = "districtId", source = "districtId"),
			@Mapping(target = "realName", source = "realName"),
			@Mapping(target = "roleId", source = "roleId"),
			@Mapping(target = "mobile", source = "mobile"),
			@Mapping(target = "email", source = "email"),
	})
	UserVo mapFrom(ShseduUser source);
}
