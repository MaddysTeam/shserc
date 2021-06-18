package com.dianda.shsedu.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.dianda.shsedu.entity.ShseduUser;
import org.apache.ibatis.annotations.Param;

public interface ShseduUserMapper extends BaseMapper<ShseduUser> {
	IPage<ShseduUser> selectUser(IPage<ShseduUser> page, @Param( Constants.WRAPPER) Wrapper<ShseduUser> wrapper);
}
