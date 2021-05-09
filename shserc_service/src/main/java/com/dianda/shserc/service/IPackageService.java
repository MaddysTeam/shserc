package com.dianda.shserc.service;

import com.dianda.common.validators.NotNull;
import com.dianda.shserc.bean.PackageSelectParams;
import com.dianda.shserc.dto.EditPackageDto;
import com.dianda.shserc.dto.EditPackageResourceDto;
import com.dianda.shserc.dto.PackageAuditDto;
import com.dianda.shserc.entity.PackageOperation;
import com.dianda.shserc.vo.PackageVo;
import com.dianda.shserc.vo.PackageVoList;
import com.dianda.shserc.vo.ResourceVoList;

import javax.validation.Valid;
import java.util.List;

public interface IPackageService {
	
	PackageVoList  find( PackageSelectParams packageSelectParams );
	
	PackageVo findById( long id);
	
	ResourceVoList findResource(long id);
	
	boolean  edit( @Valid @NotNull EditPackageDto editPackageDto );
	
	boolean audit(@Valid @NotNull PackageAuditDto packageAuditDto);
	
	boolean addPackageResource( List<EditPackageResourceDto> editPackageResourceDto);
	
	boolean addViewCount(@Valid @NotNull PackageOperation param);
	
	boolean addDownloadCount(@Valid @NotNull PackageOperation param);
	
	// favor resource or not
	boolean setOrCancelFavorite(@Valid @NotNull PackageOperation resourceOperation);
	
	// set elite resource or not
	boolean setEliteScore(@Valid @NotNull PackageOperation resourceOperation);
	
	//TODO:
	boolean setStar(@Valid @NotNull PackageOperation param);
	
}
