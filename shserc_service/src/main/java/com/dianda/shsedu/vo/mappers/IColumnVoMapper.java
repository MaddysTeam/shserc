package com.dianda.shsedu.vo.mappers;

import com.dianda.common.util.mapper.BasicMapper;
import com.dianda.shsedu.entity.Columns;
import com.dianda.shsedu.entity.News;
import com.dianda.shsedu.vo.ColumnVo;
import com.dianda.shsedu.vo.NewsVo;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

public interface IColumnVoMapper extends BasicMapper<ColumnVo, Columns> {
	IColumnVoMapper INSTANCE = Mappers.getMapper(IColumnVoMapper.class);

	@Mappings({
			@Mapping(target = "id", source = "id"),
//			@Mapping(target = "", source = ""),
//			@Mapping(target = "", source = "")
	})
	ColumnVo mapTo(Columns source);
}
