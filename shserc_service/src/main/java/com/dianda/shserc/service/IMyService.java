package com.dianda.shserc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dianda.shserc.entity.ResUser;
import com.dianda.shserc.vo.ResUserVo;


public interface IMyService extends IService<ResUser> {
	ResUserVo findResourceOperationCount( long id );
}
