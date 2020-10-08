package com.dianda.shserc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dianda.shserc.bean.ResourceSelectParams;
import com.dianda.shserc.dto.EditResourceDto;
import com.dianda.shserc.entity.Resource;
import com.dianda.shserc.vo.ResourceVo;

public interface IResourceService extends IService<Resource> {
	ResourceVo find( ResourceSelectParams params );
	ResourceVo edit( EditResourceDto model );
	ResourceVo getById( long id );
	boolean setViewCount( long userId , long id );
}
