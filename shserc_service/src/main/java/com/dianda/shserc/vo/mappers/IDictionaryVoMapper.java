package com.dianda.shserc.vo.mappers;

import com.dianda.common.entity.Dictionary;
import com.dianda.common.util.mapper.BasicMapper;
import com.dianda.common.vo.DictionaryVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IDictionaryVoMapper extends BasicMapper<DictionaryVo, Dictionary> {
	IDictionaryVoMapper INSTANCE = Mappers.getMapper(IDictionaryVoMapper.class);

	@Mappings({
			@Mapping(source = "id", target = "id"),
			@Mapping(source = "parentId", target = "parentId"),
			@Mapping(source = "relevantId", target = "relevantId"),
			@Mapping(source = "name", target = "name"),
			@Mapping(source = "code", target = "code"),
			@Mapping(source = "value", target = "value"),
			@Mapping(source = "sort", target = "sort")
	})
	DictionaryVo mapFrom(Dictionary dictionary);
}
