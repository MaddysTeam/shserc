package com.dianda.shserc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dianda.shserc.bean.RoleSelectParams;
import com.dianda.shserc.dto.EditRoleDto;
import com.dianda.shserc.dto.mappers.IEditRoleMapper;
import com.dianda.shserc.entity.ResRole;
import com.dianda.shserc.mapper.ResRoleMapper;
import com.dianda.shserc.service.IResRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dianda.shserc.util.basic.ObjectUtil;
import com.dianda.shserc.vo.ResRoleVo;
import com.dianda.shserc.vo.ResRoleVoList;
import com.dianda.shserc.vo.mappers.IRoleVoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 角色服务实现类
 *
 * @author huachao
 * @since 2020-08-12
 */
@Service
public class ResRoleServiceImpl extends ServiceImpl<ResRoleMapper, ResRole> implements IResRoleService {
	
	@Resource
	ResRoleMapper mapper;
	
	@Override
	public ResRoleVoList find( RoleSelectParams params ) {
		QueryWrapper<ResRole>  roleQueryWrapper = new QueryWrapper<> (  );
		
		return null;
	}

	@Override
	public ResRoleVo findById(long id) {
		QueryWrapper<ResRole> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq ( "r.id",id );
		ResRole resRole = mapper.selectOne(queryWrapper);
		ResRoleVo vo = ObjectUtil.isNull(resRole) ? null : IRoleVoMapper.INSTANCE.mapFrom(resRole);

		return vo;
	}

	@Override
	public boolean edit(EditRoleDto roleDto) {
		int result = 0;
		ResRole resRole = IEditRoleMapper.INSTANCE.mapFrom(roleDto);
		ResRoleVo resRealVo = findById(resRole.getId());
		if (resRole.isNewOne() && ObjectUtil.isNull(resRealVo)) {
			result = mapper.insert(resRole);
			return result > 0;
		} else {
			result = mapper.updateById(resRole);
			return result >= 0;
		}
	}

	@Override
	public boolean delete(String id) {
		return false;
	}

}
