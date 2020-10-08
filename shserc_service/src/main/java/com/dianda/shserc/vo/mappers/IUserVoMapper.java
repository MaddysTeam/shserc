package com.dianda.shserc.vo.mappers;


import com.dianda.shserc.entity.ResUser;
import com.dianda.shserc.util.mapper.BasicMapper;
import com.dianda.shserc.vo.ResUserVo;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

public interface IUserVoMapper extends BasicMapper<ResUser, ResUserVo> {
	
	IUserVoMapper INSTANCE = Mappers.getMapper ( IUserVoMapper.class );
	
	@Mappings( {
			@Mapping( source = "id", target = "id" ) ,
			@Mapping( source = "userName", target = "userName" ) ,
			@Mapping( source = "companyId", target = "companyId" ) ,
			@Mapping( source = "isDeleted", target = "isDeleted" )
	} )
	ResUserVo mapFrom( ResUser user );
	
}
