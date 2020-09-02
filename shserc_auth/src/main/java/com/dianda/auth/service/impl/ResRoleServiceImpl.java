package com.dianda.auth.service.impl;

import com.dianda.auth.dto.EditRoleDto;
import com.dianda.auth.entity.ResRole;
import com.dianda.auth.entity.ResUser;
import com.dianda.auth.mapper.ResRoleMapper;
import com.dianda.auth.service.IResRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dianda.auth.util.basic.ObjectUtil;
import org.springframework.stereotype.Service;

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
