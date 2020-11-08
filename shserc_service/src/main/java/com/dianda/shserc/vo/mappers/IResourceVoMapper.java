package com.dianda.shserc.vo.mappers;

import com.dianda.shserc.entity.Resource;
import com.dianda.shserc.util.mapper.BasicMapper;
import com.dianda.shserc.vo.ResourceVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import javax.crypto.spec.PSource;

@Mapper
public interface IResourceVoMapper extends BasicMapper<ResourceVo,Resource> {

	IResourceVoMapper INSTANCE = Mappers.getMapper(IResourceVoMapper.class);

	@Mappings({
			@Mapping(source = "id",          target = "id"),
			@Mapping(source = "title",       target = "title"),
			@Mapping(source = "keywords",       target = "keywords"),
			@Mapping(source = "author",      target = "author"),
			@Mapping(source = "authorEmail", target = "authorEmail"),
			@Mapping(source = "viewCount",   target = "viewCount"),
			@Mapping(source = "addTime",     target = "addTime"),
			@Mapping(source = "fileExtName", target = "fileExtName"),
			@Mapping(source = "state",       target = "state"),
			@Mapping(source = "deformity",   target = "deformity"),
			@Mapping(source = "resourcePath",   target = "resourcePath"),
			@Mapping(source = "coverPath",   target = "coverPath"),
			@Mapping( source="fileName",target = "fileName")
	})
	ResourceVo mapFrom(Resource resource);
}
