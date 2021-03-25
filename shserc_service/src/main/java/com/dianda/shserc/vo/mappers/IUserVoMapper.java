package com.dianda.shserc.vo.mappers;


import com.dianda.shserc.entity.ResUser;
import com.dianda.shserc.util.mapper.BasicMapper;
import com.dianda.shserc.vo.ResUserVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserVoMapper extends BasicMapper<ResUserVo, ResUser> {
	
	IUserVoMapper INSTANCE = Mappers.getMapper ( IUserVoMapper.class );
	
	@Mappings( {
			@Mapping( source = "id", target = "id" ) ,
			@Mapping( source = "userName", target = "userName" ) ,
			@Mapping( source = "companyId", target = "companyId" ),
			@Mapping( source = "photoPath", target = "photoPath" ),
			@Mapping( source = "idCard", target = "idCard" ),
			@Mapping( source = "mobile", target = "mobile" ),
			@Mapping( source = "email", target = "email" ),
			@Mapping( source = "companyName", target = "companyName" ),
			@Mapping( source = "registerTime", target = "registerTime" ),
			@Mapping( source = "lastLoginTime", target = "lastLoginTime" ),
			@Mapping( source = "loginCount", target = "loginCount" ),
			@Mapping( source = "roleName", target = "roleName" ),
			@Mapping( source = "realName", target = "realName" ),
			@Mapping( source = "roleId", target = "roleId" )
	} )
	ResUserVo mapFrom( ResUser user );
}
