package com.dianda.shserc.vo.mappers;

import com.dianda.shserc.entity.ResComment;
import com.dianda.common.util.mapper.BasicMapper;
import com.dianda.shserc.vo.CommentVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ICommentVoMapper extends BasicMapper<CommentVo, ResComment> {
	ICommentVoMapper INSTANCE = Mappers.getMapper(ICommentVoMapper.class);

	@Mappings({
			@Mapping(source = "id", target = "id"),
			@Mapping(source = "resourceId", target = "resourceId"),
			@Mapping(source = "content", target = "content"),
			@Mapping(source = "auditType", target = "auditType"),
			@Mapping(source = "auditorName", target = "auditorName"),
			@Mapping(source = "addUserName", target = "addUserName"),
			@Mapping(source = "resourceTitle", target = "resourceTitle"),
			@Mapping(source = "addTime", target = "commentTime"),
			@Mapping(source = "userPhotoPath", target = "userPhotoPath"),
	})
	CommentVo mapFrom(ResComment comment);
}
