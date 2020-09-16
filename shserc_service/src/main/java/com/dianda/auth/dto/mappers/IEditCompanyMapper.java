package com.dianda.auth.dto.mappers;

import com.dianda.auth.dto.EditCompanyDto;
import com.dianda.auth.entity.ResCompany;
import com.dianda.auth.util.mapper.BasicMapper;
import org.mapstruct.Mapper;

@Mapper
public interface IEditCompanyMapper extends BasicMapper<EditCompanyDto, ResCompany> {

}
