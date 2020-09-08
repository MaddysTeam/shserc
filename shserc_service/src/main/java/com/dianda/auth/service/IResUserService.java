package com.dianda.auth.service;

import com.dianda.auth.bean.UserSelectParams;
import com.dianda.auth.dto.EditUserDto;
import com.dianda.auth.entity.ResUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dianda.auth.vo.ResUserVo;

/**
 *
 *  user service for curd
 *
 * @author huachao
 * @since 2020-08-08
 */
public interface IResUserService extends IService<ResUser> {
	ResUserVo find(UserSelectParams params);
    ResUser edit( EditUserDto user);
	ResUser delete(long id);
}
