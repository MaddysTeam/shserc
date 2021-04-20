package com.dianda.shserc.service;

import com.dianda.shserc.bean.UserSelectParams;
import com.dianda.shserc.dto.EditUserDto;
import com.dianda.shserc.dto.EditUserRoleDto;
import com.dianda.shserc.entity.ResUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dianda.shserc.entity.ResUserRole;
import com.dianda.shserc.vo.ResUserVo;
import com.dianda.shserc.vo.ResUserVoList;

/**
 * user service
 *
 * @author huachao
 * @since 2020-08-08
 */
public interface IResUserService extends IService<ResUser> {

	ResUserVoList find(UserSelectParams params );
	
	boolean edit( EditUserDto user );
	
	boolean delete( long id );
	
	ResUserVo findById( long id );
	
	ResUserVo getByNameAndPassword(String userName,String password);
	
	Boolean editUserRole(EditUserRoleDto model);
	
}
