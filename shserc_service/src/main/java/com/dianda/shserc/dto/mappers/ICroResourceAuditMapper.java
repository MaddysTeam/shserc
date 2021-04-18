package com.dianda.shserc.dto.mappers;

import com.dianda.common.util.mapper.BasicMapper;
import com.dianda.shserc.dto.ResourceAuditDto;
import com.dianda.shserc.entity.CroResource;
import com.dianda.shserc.entity.Resource;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ICroResourceAuditMapper extends BasicMapper<CroResource, ResourceAuditDto> {

	ICroResourceAuditMapper INSTANCE= Mappers.getMapper ( ICroResourceAuditMapper.class);

	@Mappings( {
			@Mapping(source="resourceId",target="id"),
			@Mapping(source="auditorId",target="auditor"),
			@Mapping(source="auditOpinion",target="auditorOpinion")
	} )
	CroResource mapFrom( ResourceAuditDto editUserDto );
}
