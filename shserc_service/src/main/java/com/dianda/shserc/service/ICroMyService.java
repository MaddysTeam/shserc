package com.dianda.shserc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dianda.shserc.entity.ResUser;
import com.dianda.shserc.vo.CommentVoList;
import com.dianda.shserc.vo.ResUserVo;
import com.dianda.shserc.vo.ResourceOperationVoList;

public interface ICroMyService extends IService<ResUser> {
	ResUserVo findResourceOperationCount( long id );
	ResourceOperationVoList findFavorites( long id );
	ResourceOperationVoList findDownloads( long id );
	CommentVoList findComments( long id );
}
