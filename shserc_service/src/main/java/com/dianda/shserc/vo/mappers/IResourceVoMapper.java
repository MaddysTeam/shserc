package com.dianda.shserc.vo.mappers;

import com.dianda.shserc.entity.Resource;
import com.dianda.shserc.util.mapper.BasicMapper;
import com.dianda.shserc.vo.ResourceVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import javax.crypto.spec.PSource;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IResourceVoMapper extends BasicMapper<ResourceVo, Resource> {
	
	IResourceVoMapper INSTANCE = Mappers.getMapper ( IResourceVoMapper.class );
	
	@Mappings( {
			@Mapping( source = "id", target = "id" ) ,
			@Mapping( source = "title", target = "title" ) ,
			@Mapping( source = "keywords", target = "keywords" ) ,
			@Mapping( source = "author", target = "author" ) ,
			@Mapping( source = "authorEmail", target = "authorEmail" ) ,
			@Mapping( source = "viewCount", target = "viewCount" ) ,

			@Mapping( source = "downloadCount", target = "downloadCount" ) ,
			@Mapping( source = "favoriteCount", target = "favoriteCount" ) ,
			@Mapping( source = "commentCount", target = "commentCount" ) ,
			@Mapping( source = "starCount", target = "starCount" ) ,
			@Mapping( source = "starTotal", target = "starTotal" ) ,

			@Mapping( source = "subjectId", target = "subjectId" ) ,
			@Mapping( source = "gradeId", target = "gradeId" ) ,
			@Mapping( source = "stageId", target = "stageId" ) ,
			@Mapping( source = "importSourceId", target = "importSourceId" ) ,
			@Mapping( source = "schoolTypeId", target = "schoolTypeId" ) ,
			@Mapping( source = "domainId", target = "domainId" ) ,

			@Mapping( source = "addTime", target = "addTime" ) ,
			@Mapping( source = "fileExtName", target = "fileExtName" ) ,
			@Mapping( source = "state", target = "state" ) ,
			@Mapping( source = "deformity", target = "deformity" ) ,
			@Mapping( source = "deformityId", target = "deformityId" ) ,
			@Mapping( source = "resourcePath", target = "resourcePath" ) ,
			@Mapping( source = "coverPath", target = "coverPath" ) ,
			@Mapping( source = "fileName", target = "fileName" ) ,
			@Mapping( source = "fileSize", target = "fileSize" ),

			@Mapping( source = "auditor", target = "auditor" ),
			@Mapping( source = "auditorOpinion", target = "auditorOpinion" )
	} )
	ResourceVo mapFrom( Resource resource );
}
