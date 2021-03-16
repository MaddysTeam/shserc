package com.dianda.shserc.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.dianda.shserc.entity.ResRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * role mapper interface
 *
 * @author huachao
 * @since 2020-08-17
 */
public interface ResRoleMapper extends BaseMapper<ResRole> {
	IPage<ResRole> selectRole(IPage<ResRole> page, @Param(Constants.WRAPPER)Wrapper<ResRole> wrapper);
}
