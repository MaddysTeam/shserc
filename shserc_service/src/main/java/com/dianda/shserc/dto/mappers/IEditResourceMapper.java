package com.dianda.shserc.dto.mappers;

import com.dianda.shserc.dto.EditResourceDto;
import com.dianda.shserc.dto.ResourceAuditDto;
import com.dianda.shserc.entity.Resource;
import com.dianda.shserc.util.mapper.BasicMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IEditResourceMapper extends BasicMapper<Resource, EditResourceDto> {
	
	 IEditResourceMapper INSTANCE= Mappers.getMapper ( IEditResourceMapper.class);
	
	@Mappings( {
			@Mapping(source="id",target="id"),
			@Mapping(source="title",target="title"),
			@Mapping(source="deformityId",target="deformityId"),
			@Mapping(source="author",target="author"),
			@Mapping(source="keywords",target="keywords"),
			@Mapping(source="resourcePath",target="resourcePath"),
			@Mapping(source="authorCompany",target="authorCompany"),
			@Mapping(source="authorEmail",target="authorEmail"),
			@Mapping(source="authorPhone",target="authorPhone"),
			@Mapping(source="description",target="description"),
			@Mapping(source="fileName",target="fileName"),
			@Mapping(source="subjectId",target="subjectId"),
			@Mapping(source="gradeId",target="gradeId"),
			@Mapping(source="importSourceId",target="importSourceId"),
			@Mapping(source="schoolTypeId",target="schoolTypeId"),
			@Mapping(source="domainId",target="domainId"),
			@Mapping(source="coverPath",target="coverPath"),
			@Mapping(source="stateId",target="stateId"),
			@Mapping(source="resourceTypeId",target="resourceTypeId")
	} )
	Resource mapFrom(EditResourceDto editUserDto );
}



