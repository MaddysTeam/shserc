package com.dianda.shserc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dianda.shserc.bean.ResourceSelectParams;
import com.dianda.shserc.dto.EditResourceDto;
import com.dianda.shserc.dto.ResourceAuditDto;
import com.dianda.shserc.entity.ResourceOperation;
import com.dianda.shserc.entity.Resource;
import com.dianda.common.validators.NotNull;
import com.dianda.shserc.vo.ResourceVo;
import com.dianda.shserc.vo.ResourceVoList;
import com.dianda.shserc.vo.ScoreVo;
import com.dianda.shserc.vo.ScoreVoList;

import javax.validation.Valid;

public interface IResourceService extends IService<Resource> {

	ResourceVoList find( ResourceSelectParams params);

	boolean edit(@Valid @NotNull EditResourceDto model);

	boolean audit(@Valid @NotNull ResourceAuditDto model);

	ResourceVo findById(long id);

	boolean addViewCount(@Valid @NotNull ResourceOperation param);

	boolean addDownloadCount(@Valid @NotNull ResourceOperation param);

	// favor resource or not
	boolean setOrCancelFavorite(@Valid @NotNull ResourceOperation resourceOperation);

	// set elite resource or not
	boolean setEliteScore(@Valid @NotNull ResourceOperation resourceOperation);

	//TODO:
	boolean setStar(@Valid @NotNull ResourceOperation param);
	
	ScoreVoList findStars(@Valid long resourceId);
	
}
