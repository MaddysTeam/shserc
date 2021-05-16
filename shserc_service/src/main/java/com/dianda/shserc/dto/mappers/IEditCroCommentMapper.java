package com.dianda.shserc.dto.mappers;

import com.dianda.common.util.mapper.BasicMapper;
import com.dianda.shserc.dto.EditCommentDto;
import com.dianda.shserc.entity.CroComment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IEditCroCommentMapper extends BasicMapper<CroComment, EditCommentDto> {

	IEditCroCommentMapper INSTANCE = Mappers.getMapper( IEditCroCommentMapper.class);

	@Mappings({
			@Mapping(source = "id", target = "id"),
			@Mapping(source = "auditTypeId", target = "auditTypeId"),
			@Mapping(source="resourceId",target="resourceId"),
			@Mapping(source="auditor",target="auditor"),
			@Mapping(source="content",target="content")
	})
	CroComment mapFrom( EditCommentDto dto );
}
