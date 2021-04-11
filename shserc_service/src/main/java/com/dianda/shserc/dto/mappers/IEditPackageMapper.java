package com.dianda.shserc.dto.mappers;

import com.dianda.common.util.mapper.BasicMapper;
import com.dianda.shserc.dto.EditPackageDto;
import com.dianda.shserc.dto.EditResourceDto;
import com.dianda.shserc.entity.Package;
import com.dianda.shserc.entity.Resource;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IEditPackageMapper extends BasicMapper<Package, EditPackageDto> {
	
	IEditPackageMapper INSTANCE= Mappers.getMapper ( IEditPackageMapper.class);
	
	@Mappings( {
			@Mapping(source="id",target="id"),
			@Mapping(source="title",target="title"),
			@Mapping(source="deformityId",target="deformityId"),
			@Mapping(source="author",target="author"),
			@Mapping(source="keywords",target="keywords"),
			@Mapping(source="authorCompany",target="authorCompany"),
			@Mapping(source="authorEmail",target="authorEmail"),
			@Mapping(source="authorPhone",target="authorPhone"),
			@Mapping(source="description",target="description"),
			@Mapping(source="subjectId",target="subjectId"),
			@Mapping(source="gradeId",target="gradeId"),
			@Mapping(source="schoolTypeId",target="schoolTypeId"),
			@Mapping(source="domainId",target="domainId"),
			@Mapping(source="coverPath",target="coverPath"),
			@Mapping(source="stateId",target="stateId"),
	} )
	Package mapFrom( EditPackageDto editUserDto );
}



