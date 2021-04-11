package com.dianda.shserc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dianda.shserc.bean.PackageSelectParams;
import com.dianda.shserc.dto.EditPackageDto;
import com.dianda.shserc.dto.EditPackageResourceDto;
import com.dianda.shserc.entity.Package;
import com.dianda.shserc.mapper.PackageMapper;
import com.dianda.shserc.service.IPackageService;
import com.dianda.shserc.vo.PackageVo;
import com.dianda.shserc.vo.PackageVoList;

import java.util.List;

public class PackageServiceImpl extends ServiceImpl<PackageMapper, Package> implements IPackageService {
	

	public boolean bindPackageResource( EditPackageResourceDto editPackageResourceDto ) {
		return false;
	}
	
	
	@Override
	public PackageVoList find( PackageSelectParams packageSelectParams ) {
		return null;
	}
	
	@Override
	public PackageVo findById( long id ) {
		return null;
	}
	
	@Override
	public boolean edit( EditPackageDto editPackageDto ) {
		return false;
	}
	
	@Override
	public boolean bindPackageResource( List<EditPackageResourceDto> editPackageResourceDto ) {
		return false;
	}
	
}
