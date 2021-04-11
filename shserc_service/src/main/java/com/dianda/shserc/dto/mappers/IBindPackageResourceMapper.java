package com.dianda.shserc.dto.mappers;

import com.dianda.common.util.mapper.BasicMapper;
import com.dianda.shserc.dto.EditPackageResourceDto;
import com.dianda.shserc.entity.PackageResource;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

public interface IBindPackageResourceMapper extends BasicMapper<PackageResource, EditPackageResourceDto> {
	
	IBindPackageResourceMapper INSTANCE = Mappers.getMapper ( IBindPackageResourceMapper.class );
	
	@Mappings( {
			@Mapping( target = "packageId", source = "packageId" ) ,
			@Mapping( target = "resourceId", source = "resourceId" )
	} )
	PackageResource mapFrom( EditPackageResourceDto editPackageResourceDto );
}
