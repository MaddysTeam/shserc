package com.dianda.shsedu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dianda.shsedu.bean.UserSelectParams;
import com.dianda.shsedu.dto.EditUserDto;
import com.dianda.shsedu.entity.ShseduUser;
import com.dianda.shsedu.vo.UserVo;
import com.dianda.shsedu.vo.UserVoList;

public interface IUserService extends IService<ShseduUser> {

	UserVoList findByPhrase(UserSelectParams userSelectParams);

	UserVo findById(long id );

	boolean edit(EditUserDto editUserDto);

	boolean hasPermissions(long id,long permissionId); //NOTE: get permission from two ways :   user permissions and user role permissions

	boolean hasInRole(long id,long roleId);
}
