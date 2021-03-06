package com.dianda.shserc.vo.mappers;

import com.dianda.common.util.mapper.BasicMapper;
import com.dianda.shserc.entity.ResourceOperation;
import com.dianda.shserc.vo.ResourceOperationVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper( unmappedTargetPolicy = ReportingPolicy.IGNORE )
public interface IResourceScoreVoMapper extends BasicMapper<ResourceOperationVo, ResourceOperation> {
	IResourceScoreVoMapper INSTANCE = Mappers.getMapper ( IResourceScoreVoMapper.class );
	
	@Mappings( {
			@Mapping( source = "resourceId", target = "resourceId" ) ,
			@Mapping( source = "userId", target = "userId" ) ,
			@Mapping( source = "operIntResult", target = "score" ) ,
	} )
	ResourceOperationVo mapFrom( ResourceOperation resourceOperation );
}


