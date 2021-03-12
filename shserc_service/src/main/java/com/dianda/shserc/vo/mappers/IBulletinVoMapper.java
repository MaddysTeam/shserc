package com.dianda.shserc.vo.mappers;


import com.dianda.shserc.entity.Bulletin;
import com.dianda.shserc.entity.ResUser;
import com.dianda.shserc.util.mapper.BasicMapper;
import com.dianda.shserc.vo.BulletinVo;
import com.dianda.shserc.vo.ResUserVo;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

public interface IBulletinVoMapper extends BasicMapper<Bulletin, BulletinVo> {

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
