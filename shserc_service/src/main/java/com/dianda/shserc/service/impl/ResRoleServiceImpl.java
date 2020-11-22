package com.dianda.shserc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dianda.shserc.bean.RoleSelectParams;
import com.dianda.shserc.dto.EditRoleDto;
import com.dianda.shserc.entity.ResRole;
import com.dianda.shserc.mapper.ResRoleMapper;
import com.dianda.shserc.service.IResRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dianda.shserc.util.basic.ObjectUtil;
import com.dianda.shserc.vo.ResRoleVoList;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author huachao
 * @since 2020-08-12
 */
@Service
public class ResRoleServiceImpl extends ServiceImpl<ResRoleMapper, ResRole> implements IResRoleService {
	
	@Resource
	ResRoleMapper roleMapper;
	
	@Override
	public ResRoleVoList find( RoleSelectParams params ) {
		QueryWrapper<ResRole>  roleQueryWrapper = new QueryWrapper<> (  );
		
		return null;
	}
	
	@Override
	public ResRole edit(EditRoleDto roleDto) {
		if (ObjectUtil.isNull(roleDto))
			return null;


		return null;
	}

	@Override
	public boolean delete(String id) {
		return false;
	}

}
