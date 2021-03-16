package com.dianda.shserc.dto.mappers;

import com.dianda.shserc.dto.ResourceAuditDto;
import com.dianda.shserc.entity.Resource;
import com.dianda.shserc.util.mapper.BasicMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IResourceAuditMapper extends BasicMapper<Resource, ResourceAuditDto> {

	IResourceAuditMapper INSTANCE= Mappers.getMapper ( IResourceAuditMapper.class);

	@Mappings( {
			@Mapping(source="resourceId",target="id"),
			@Mapping(source="auditorId",target="auditor"),
			@Mapping(source="auditOpinion",target="auditorOpinion")
	} )
	Resource mapFrom(ResourceAuditDto editUserDto);
}
