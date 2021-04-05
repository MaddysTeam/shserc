package com.dianda.shserc.dto.mappers;

import com.dianda.shserc.dto.EditUserDto;
import com.dianda.shserc.entity.ResUser;
import com.dianda.common.util.mapper.BasicMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IEditUserMapper extends BasicMapper<ResUser, EditUserDto> {
	
	 IEditUserMapper INSTANCE= Mappers.getMapper ( IEditUserMapper.class);
	
	@Mappings( {
			@Mapping(source="id",target="id"),
			@Mapping( source="userName", target="userName" ),
			@Mapping( source="companyId", target="companyId" ),
			@Mapping( source="idCard", target="idCard" ),
			@Mapping( source="realName", target="realName" ),
			@Mapping( source="mobile", target="mobile" ),
			@Mapping( source="email", target="email" ),
			@Mapping( source="photoPath", target="photoPath" )
	} )
	ResUser mapFrom(EditUserDto editUserDto);
	
}