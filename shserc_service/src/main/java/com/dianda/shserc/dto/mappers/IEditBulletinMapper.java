package com.dianda.shserc.dto.mappers;

import com.dianda.shserc.dto.EditBulletinDto;
import com.dianda.shserc.dto.EditStateDto;
import com.dianda.shserc.entity.Bulletin;
import com.dianda.common.util.mapper.BasicMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IEditBulletinMapper extends BasicMapper<Bulletin, EditBulletinDto> {

	IEditBulletinMapper INSTANCE = Mappers.getMapper(IEditBulletinMapper.class);

	@Mappings({
			@Mapping(source = "id", target = "id"),
			@Mapping(source = "typeId", target = "typeId"),
			@Mapping(source = "title", target = "title"),
			@Mapping(source = "content",target="content"),
			@Mapping(source = "filePath",target="filePath"),
			@Mapping(source = "fileName",target="fileName"),
			@Mapping(source = "fileExtName",target="fileExtName"),
			@Mapping(source = "fileSize",target="fileSize")
	})
	Bulletin mapFrom(EditBulletinDto editUserDto);
	
	@Mappings({
			@Mapping(source = "booleanState", target = "top"),
			@Mapping(source = "targetId", target = "id")
	})
	Bulletin mapFrom( EditStateDto editStateDto );

}