package com.dianda.shserc.vo.mappers;

import com.dianda.shserc.entity.ResReal;
import com.dianda.common.util.mapper.BasicMapper;
import com.dianda.shserc.vo.ResRealVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IRealVoMapper extends BasicMapper<ResRealVo, ResReal> {
	
	IRealVoMapper INSTANCE = Mappers.getMapper ( IRealVoMapper.class );
	
	@Mappings( {
			@Mapping(source = "id", target = "id"),
			@Mapping(source = "cardNo", target = "cardNo"),
			@Mapping(source="idCard",target="idCard"),
			@Mapping(source="realName",target="realName"),
			@Mapping(source="companyName",target="companyName"),
			@Mapping(source="companyId",target="companyId"),
			@Mapping(source="cardPassword",target="cardPassword")
	} )
	ResRealVo mapFrom( ResReal resReal );
}
