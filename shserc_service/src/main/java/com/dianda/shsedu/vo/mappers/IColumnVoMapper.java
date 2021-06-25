package com.dianda.shsedu.vo.mappers;

import com.dianda.common.util.mapper.BasicMapper;
import com.dianda.shsedu.entity.Columns;
import com.dianda.shsedu.vo.ColumnVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IColumnVoMapper extends BasicMapper<ColumnVo, Columns> {
	IColumnVoMapper INSTANCE = Mappers.getMapper(IColumnVoMapper.class);

	@Mappings({
			@Mapping(target = "id", source = "id"),
			@Mapping(target = "title", source = "title"),
//			@Mapping(target = "isMenu", source = "isMenu"),
			@Mapping(target = "parentId", source = "parentId"),
			@Mapping(target="statusId",source = "statusId")
	})
	ColumnVo mapFrom(Columns source);
}
