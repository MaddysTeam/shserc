package com.dianda.shserc.vo.mappers;

import com.dianda.shserc.entity.Dictionary;
import com.dianda.shserc.vo.DictionaryVo;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

public interface IDictionaryVoMapper {
	IDictionaryVoMapper INSTANCE = Mappers.getMapper(IDictionaryVoMapper.class);

	@Mappings({
			@Mapping(source = "id",target = "id"),
			@Mapping(source = "parentId",target = "parentId"),
			@Mapping(source = "name", target="name"),
			@Mapping(source = "code", target="code"),
			@Mapping(source = "value", target="value"),
			@Mapping(source = "sort", target="sort")
	})
	DictionaryVo mapFrom(Dictionary dictionary);
}
