package com.dianda.shserc.dto.mappers;

import com.dianda.shserc.dto.EditCommentDto;
import com.dianda.shserc.entity.ResComment;
import com.dianda.shserc.util.mapper.BasicMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IEditCommentMapper extends BasicMapper<ResComment, EditCommentDto> {

	IEditCommentMapper INSTANCE = Mappers.getMapper(IEditCommentMapper.class);

	@Mappings({
			@Mapping(source = "id", target = "id"),
			@Mapping(source = "auditTypeId", target = "auditTypeId"),
			@Mapping(source="resourceId",target="resourceId"),
			@Mapping(source="userId",target="userId"),
			@Mapping(source="content",target="content")
	})
	ResComment mapFrom(EditCommentDto dto);
}
