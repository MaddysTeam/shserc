package com.dianda.shserc.service;

import com.dianda.shserc.bean.PackageSelectParams;
import com.dianda.shserc.dto.EditPackageDto;
import com.dianda.shserc.dto.EditPackageResourceDto;
import com.dianda.shserc.vo.PackageVo;
import com.dianda.shserc.vo.PackageVoList;

import java.util.List;

public interface IPackageService {
	
	PackageVoList  find( PackageSelectParams packageSelectParams );
	
	PackageVo findById( long id);
	
	boolean  edit( EditPackageDto editPackageDto );
	
	boolean bindPackageResource( List<EditPackageResourceDto> editPackageResourceDto);
	
}
