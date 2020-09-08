package com.dianda.auth.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dianda.auth.common.Constant;
import com.dianda.auth.entity.ResUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author huachao
 * @since 2020-08-08
 */
public interface ResUserMapper extends BaseMapper<ResUser> {
	IPage<ResUser> selectUsers(IPage<ResUser> page, @Param(Constants.WRAPPER)Wrapper<ResUser> wrapper);
}
