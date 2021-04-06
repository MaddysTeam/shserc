package com.dianda.shserc.service;

import com.dianda.shserc.dto.EditPackageResourceDto;

public interface IPackageService {

	boolean bindPackageResource(EditPackageResourceDto editPackageResourceDto);

	boolean unbindPackageResource(EditPackageResourceDto editPackageResourceDto);
}
