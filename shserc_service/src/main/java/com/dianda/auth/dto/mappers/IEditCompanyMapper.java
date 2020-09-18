package com.dianda.auth.dto.mappers;

import com.dianda.auth.dto.EditCompanyDto;
import com.dianda.auth.entity.ResCompany;
import com.dianda.auth.util.mapper.BasicMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IEditCompanyMapper extends BasicMapper<EditCompanyDto, ResCompany> {
	IEditCompanyMapper INSTANCE = Mappers.getMapper(IEditCompanyMapper.class);

	@Mappings({
			@Mapping(source = "id", target = "id"),
			@Mapping(source = "parentId", target = "parentId"),
			@Mapping(source = "companyName", target = "companyName"),
	})
	ResCompany mapFrom(EditCompanyDto editCompanyDto);
}
