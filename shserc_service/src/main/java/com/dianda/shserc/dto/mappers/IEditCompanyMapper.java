package com.dianda.shserc.dto.mappers;

import com.dianda.shserc.dto.EditCompanyDto;
import com.dianda.shserc.entity.ResCompany;
import com.dianda.shserc.util.mapper.BasicMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IEditCompanyMapper extends BasicMapper<ResCompany, EditCompanyDto> {
	
	IEditCompanyMapper INSTANCE = Mappers.getMapper ( IEditCompanyMapper.class );
	
	@Mappings( {
			@Mapping( source = "id", target = "id" ) ,
			@Mapping( source = "parentId", target = "parentId" ) ,
			@Mapping( source = "name", target = "companyName" ) ,
	} )
	ResCompany mapFrom( EditCompanyDto editCompanyDto );
}
