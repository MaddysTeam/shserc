package com.dianda.shserc.vo.mappers;

import com.dianda.shserc.entity.Resource;
import com.dianda.shserc.vo.ResourceVo;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

public interface IResourceVoMapper {
	
	IResourceVoMapper INSTANCE = Mappers.getMapper ( IResourceVoMapper.class );
	
	@Mappings( {
			@Mapping( source = "id", target = "id" ) ,
			@Mapping( source = "title", target = "title" ) ,
			@Mapping( source = "author", target = "author" ) ,
			@Mapping( source = "authorEmail", target = "authorEmail" ) ,
			@Mapping( source = "viewCount", target = "viewCount" ) ,
			@Mapping( source = "addTime", target = "addTime" ) ,
			@Mapping( source = "fileExtName", target = "fileExtName" ) ,
			@Mapping( source = "mediumType", target = "mediumType" )
	} )
	ResourceVo mapFrom( Resource resource );
}
