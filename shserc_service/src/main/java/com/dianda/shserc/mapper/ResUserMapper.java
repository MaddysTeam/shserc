package com.dianda.shserc.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.dianda.shserc.entity.ResUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dianda.shserc.entity.ResUserRole;
import org.apache.ibatis.annotations.Param;

/**
 * user mapper interface
 *
 * @author huachao
 * @since 2020-08-17
 */
public interface ResUserMapper extends BaseMapper<ResUser> {

	IPage<ResUser> selectUsers(IPage<ResUser> page, @Param(Constants.WRAPPER) Wrapper<ResUser> wrapper);

	ResUserRole selectUserRole(@Param(Constants.WRAPPER) Wrapper<ResUserRole> wrapper);

	int insertUserRole(ResUserRole resUserRole);

	int deleteUserRole(ResUserRole resUserRole);

}
