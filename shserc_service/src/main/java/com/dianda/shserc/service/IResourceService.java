package com.dianda.shserc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dianda.shserc.bean.ResourceSelectParams;
import com.dianda.shserc.dto.EditResourceDto;
import com.dianda.shserc.entity.ResourceOperation;
import com.dianda.shserc.entity.Resource;
import com.dianda.shserc.vo.ResourceVo;
import com.dianda.shserc.vo.ResourceVoList;

public interface IResourceService extends IService<Resource> {
	ResourceVoList find( ResourceSelectParams params );
	ResourceVo edit( EditResourceDto model );
	ResourceVo getById( long id );
	boolean addViewCount( ResourceOperation param);
	boolean addPraiseCount(ResourceOperation param);
	boolean addDownloadCount(ResourceOperation param);
}
