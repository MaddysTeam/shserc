package com.dianda.shserc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dianda.common.validators.NotNull;
import com.dianda.shserc.bean.CroResourceSelectParams;
import com.dianda.shserc.dto.EditCroResourceDto;
import com.dianda.shserc.dto.ResourceAuditDto;
import com.dianda.shserc.entity.CroResource;
import com.dianda.shserc.entity.ResourceOperation;
import com.dianda.shserc.vo.*;

import javax.validation.Valid;

public interface ICroResourceService extends IService<CroResource> {

	CroResourceVoList find( CroResourceSelectParams params );

	boolean edit( @Valid @NotNull EditCroResourceDto model );

	boolean audit( @Valid @NotNull ResourceAuditDto model );

	CroResourceVo findById( long id );

	boolean addViewCount( @Valid @NotNull ResourceOperation param );

	boolean addDownloadCount( @Valid @NotNull ResourceOperation param );

	// favor resource or not
	boolean setOrCancelFavorite( @Valid @NotNull ResourceOperation resourceOperation );

	// set elite resource or not
	boolean setEliteScore( @Valid @NotNull ResourceOperation resourceOperation );
	
	boolean setStar( @Valid @NotNull ResourceOperation param );
	
	ScoreVoList findStars( @Valid long resourceId );

}
