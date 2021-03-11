package com.dianda.shserc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dianda.shserc.bean.ResourceSelectParams;
import com.dianda.shserc.dto.EditResourceDto;
import com.dianda.shserc.entity.ResourceOperation;
import com.dianda.shserc.entity.Resource;
import com.dianda.shserc.validators.NotNull;
import com.dianda.shserc.vo.ResourceVo;
import com.dianda.shserc.vo.ResourceVoList;

import javax.validation.Valid;

public interface IResourceService extends IService<Resource> {

	ResourceVoList find( ResourceSelectParams params);

	boolean edit(@Valid @NotNull EditResourceDto model);

	ResourceVo findById(long id);

	boolean addViewCount(@Valid @NotNull ResourceOperation param);

	boolean addDownloadCount(@Valid @NotNull ResourceOperation param);

	// favor resource or not
	boolean setOrCancelFavorite(@Valid @NotNull ResourceOperation resourceOperation);

	// set elite resource or not
	boolean setEliteScore(@Valid @NotNull ResourceOperation resourceOperation);

	//TODO:
	boolean setStar(@Valid @NotNull ResourceOperation param);

}
