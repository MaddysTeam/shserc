package com.dianda.shserc.service;

import com.dianda.shserc.bean.UserSelectParams;
import com.dianda.shserc.dto.EditUserDto;
import com.dianda.shserc.entity.ResUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dianda.shserc.vo.ResUserVo;

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
