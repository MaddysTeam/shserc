package com.dianda.shserc.vo.mappers;

import com.dianda.shserc.entity.ResComment;
import com.dianda.shserc.util.mapper.BasicMapper;
import com.dianda.shserc.vo.CommentVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ICommentVoMapper extends BasicMapper<CommentVo, ResComment> {
	ICommentVoMapper INSTANCE = Mappers.getMapper(ICommentVoMapper.class);

	@Mappings({
			@Mapping(source = "id", target = "id"),
			@Mapping(source = "resourceId",target="resourceId"),
			@Mapping(source = "content",target = "content"),
			@Mapping(source="auditType",target = "auditType")
	})
	CommentVo mapFrom(ResComment comment);
}
