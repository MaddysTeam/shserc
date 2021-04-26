package com.dianda.shserc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dianda.shserc.entity.ResUser;
import com.dianda.shserc.entity.Resource;
import com.dianda.shserc.vo.ResUserVo;
import com.dianda.shserc.vo.ResourceOperationVoList;
import com.dianda.shserc.vo.ResourceVo;

import java.util.List;


public interface IMyService extends IService<ResUser> {
	ResUserVo findResourceOperationCount( long id );
	ResourceOperationVoList findFavorites( long id);
	ResourceOperationVoList findDownloads(long id);
	ResourceOperationVoList findComments(long id);
}
