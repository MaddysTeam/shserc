package com.dianda.shserc.vo.mappers;

import com.dianda.common.util.mapper.BasicMapper;
import com.dianda.shserc.entity.CroResource;
import com.dianda.shserc.vo.CroResourceVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ICroResourceVoMapper extends BasicMapper<CroResourceVo, CroResource> {
	
	ICroResourceVoMapper INSTANCE = Mappers.getMapper ( ICroResourceVoMapper.class );
	
	@Mappings( {
			@Mapping( source = "id", target = "id" ) ,
			@Mapping( source = "title", target = "title" ) ,
			@Mapping( source = "keywords", target = "keywords" ) ,
			@Mapping( source = "description", target = "description" ) ,
			@Mapping( source = "author", target = "author" ) ,
			@Mapping( source = "authorCompany", target = "authorCompany" ) ,
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
			@Mapping( source = "stateId", target = "stateId" ) ,
			@Mapping( source = "learnFromId", target = "learnFromId" ) ,
			@Mapping( source = "resourceTypeId", target = "resourceTypeId" ) ,
			
			//@Mapping( source = "subject", target = "subject" ) ,
			//@Mapping( source = "grade", target = "grade" ) ,
			@Mapping( source = "stage", target = "stage" ) ,
			@Mapping( source = "importSource", target = "importSource" ) ,
			@Mapping( source = "schoolType", target = "schoolType" ) ,
			@Mapping( source = "domain", target = "domain" ) ,
			@Mapping( source = "learnFrom", target = "learnFrom" ) ,
			@Mapping( source = "resourceType", target = "resourceType" ) ,
			
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
			@Mapping( source = "auditorOpinion", target = "auditorOpinion" ),
			@Mapping( source = "createTypeId", target = "createTypeId" ),
			@Mapping( source = "sourceUrl", target = "sourceUrl" )
			
	} )
	CroResourceVo mapFrom( CroResource resource );
}
