package com.dianda.auth.service;

import com.dianda.auth.entity.ResUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dianda.auth.vo.ResUserVo;

import java.util.List;

/**
 *
 *  user service for curd
 *
 * @author huachao
 * @since 2020-08-08
 */
public interface IResUserService extends IService<ResUser> {
	ResUserVo findByPhrase( String phrase, Integer pageIndex, Integer pageSize);
    ResUser add(ResUser user);
    ResUser edit(ResUser user);
	ResUser delete(String id);
}
