package com.dianda.shserc.vo.mappers;

import com.dianda.shserc.entity.Bulletin;
import com.dianda.common.util.mapper.BasicMapper;
import com.dianda.shserc.vo.BulletinVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IBulletinVoMapper extends BasicMapper<BulletinVo,Bulletin> {

	IBulletinVoMapper INSTANCE = Mappers.getMapper ( IBulletinVoMapper.class );

	@Mappings( {
			@Mapping( source = "id", target = "id" ) ,
			@Mapping( source = "title", target = "title" ) ,
			@Mapping( source = "content", target = "content" ) ,
			@Mapping( source = "top", target = "top" ),
			@Mapping( source = "filePath", target = "filePath" )
	} )
	BulletinVo mapFrom(Bulletin bulletin);
}
