package com.dianda.shserc.vo.mappers;

import com.dianda.shserc.entity.Menu;
import com.dianda.shserc.util.mapper.BasicMapper;
import com.dianda.shserc.vo.MenuVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IMenuVoMapper extends BasicMapper<MenuVo, Menu> {
	IMenuVoMapper INSTANCE= Mappers.getMapper(IMenuVoMapper.class);

	@Mappings({
			@Mapping(source="id",target="id"),
			@Mapping(source="parentId",target="parentId"),
			@Mapping(source="title",target="name"),
			@Mapping(source = "path",target = "path"),
			@Mapping(source = "level",target = "level")
	})
	MenuVo mapFrom(Menu menu);
}
