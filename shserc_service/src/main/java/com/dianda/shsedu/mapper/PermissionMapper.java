package com.dianda.shsedu.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.dianda.shsedu.entity.Permission;
import org.apache.ibatis.annotations.Param;

public interface PermissionMapper extends BaseMapper<Permission> {
	IPage<Permission> selectPermissions(IPage<Permission> page, @Param( Constants.WRAPPER) Wrapper<Permission> wrapper);
}
