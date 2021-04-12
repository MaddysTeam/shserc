package com.dianda.shserc.dto.mappers;


import com.dianda.common.util.mapper.BasicMapper;
import com.dianda.shserc.dto.EditPackageResourceDto;
import com.dianda.shserc.entity.PackageOperation;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

public interface IBindPackageResourceMapper extends BasicMapper<PackageOperation, EditPackageResourceDto> {
	
	IBindPackageResourceMapper INSTANCE = Mappers.getMapper ( IBindPackageResourceMapper.class );
	
	@Mappings( {
			@Mapping( target = "packageId", source = "packageId" ) ,
			@Mapping( target = "resourceId", source = "resourceId" )
	} )
	PackageOperation mapFrom( EditPackageResourceDto editPackageResourceDto );
}
