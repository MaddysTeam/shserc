package com.dianda.shserc.dto.mappers;

import com.dianda.shserc.dto.EditRealDto;
import com.dianda.shserc.entity.ResReal;
import com.dianda.common.util.mapper.BasicMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IEditRealMapper extends BasicMapper<ResReal, EditRealDto> {

	IEditRealMapper INSTANCE = Mappers.getMapper(IEditRealMapper.class);

	@Mappings({
			@Mapping(source = "id", target = "id"),
			@Mapping(source = "cardNo", target = "cardNo"),
			@Mapping(source="companyId",target="companyId"),
			@Mapping(source="cardPassword",target="cardPassword")
	})
	ResReal mapFrom(EditRealDto dto);
}
