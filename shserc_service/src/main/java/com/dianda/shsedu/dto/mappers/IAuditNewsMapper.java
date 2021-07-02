package com.dianda.shsedu.dto.mappers;

import com.dianda.common.util.mapper.BasicMapper;
import com.dianda.shsedu.dto.AuditNewsDto;
import com.dianda.shsedu.entity.News;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

public interface  IAuditNewsMapper extends BasicMapper<News, AuditNewsDto> {
	IAuditNewsMapper INSTANCE = Mappers.getMapper(IAuditNewsMapper.class);

	@Mappings({
			@Mapping(target = "id", source = "id"),
			@Mapping(target = "auditorId", source = "auditorId"),
			@Mapping(target = "auditTime", source = "auditTime"),
			@Mapping(target = "statusId", source = "statusId"),
	})
	News mapFrom(AuditNewsDto source);
}
