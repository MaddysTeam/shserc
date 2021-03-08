package com.dianda.shserc.vo.mappers;


import com.dianda.shserc.entity.ResUser;
import com.dianda.shserc.util.mapper.BasicMapper;
import com.dianda.shserc.vo.ResUserVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IUserVoMapper extends BasicMapper<ResUserVo, ResUser> {
	
	IUserVoMapper INSTANCE = Mappers.getMapper ( IUserVoMapper.class );
	
	@Mappings( {
			@Mapping( source = "id", target = "id" ) ,
			@Mapping( source = "userName", target = "userName" ) ,
			@Mapping( source = "companyId", target = "companyId" )
	} )
	ResUserVo mapFrom( ResUser user );
}
