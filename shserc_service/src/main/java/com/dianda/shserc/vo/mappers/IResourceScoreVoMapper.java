package com.dianda.shserc.vo.mappers;

import com.dianda.common.util.mapper.BasicMapper;
import com.dianda.shserc.entity.ResourceOperation;
import com.dianda.shserc.vo.ScoreVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper( unmappedTargetPolicy = ReportingPolicy.IGNORE )
public interface IResourceScoreVoMapper extends BasicMapper<ScoreVo, ResourceOperation> {
	IResourceScoreVoMapper INSTANCE = Mappers.getMapper ( IResourceScoreVoMapper.class );
	
	@Mappings( {
			@Mapping( source = "resourceId", target = "targetId" ) ,
			@Mapping( source = "userId", target = "userId" ) ,
			@Mapping( source = "operIntResult", target = "score" ) ,
	} )
	ScoreVo mapFrom( ResourceOperation resourceOperation );
}
