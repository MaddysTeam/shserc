package com.dianda.shserc.service;

import com.dianda.shserc.bean.UserSelectParams;
import com.dianda.shserc.dto.EditUserDto;
import com.dianda.shserc.entity.ResUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dianda.shserc.entity.ResUserRole;
import com.dianda.shserc.vo.ResUserVo;

/**
 * user service
 *
 * @author huachao
 * @since 2020-08-08
 */
public interface IResUserService extends IService<ResUser> {
	ResUserVo find( UserSelectParams params );
	
	ResUserVo edit( EditUserDto user );
	
	ResUserVo delete( long id );
	
	ResUserVo getById( long id );
	
	ResUserVo getByNameAndPassword(String userName,String password);
	
	Boolean addUserRole( long userId , long roleId );
	
	Boolean deleteUserRole( long userId , long roleId );
}
