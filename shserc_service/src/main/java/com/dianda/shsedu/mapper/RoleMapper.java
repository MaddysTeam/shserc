package com.dianda.shsedu.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.dianda.shsedu.entity.Roles;
import org.apache.ibatis.annotations.Param;

public interface RoleMapper extends BaseMapper<Roles> {
	IPage<Roles> selectRoles(IPage<Roles> page, @Param( Constants.WRAPPER) Wrapper<Roles> wrapper);
}
