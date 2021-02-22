package com.dianda.shserc.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.dianda.shserc.entity.ResReal;
import org.apache.ibatis.annotations.Param;

public interface ResRealMapper extends BaseMapper<ResReal> {
	IPage<ResReal> selectReal(IPage<ResReal> page, @Param(Constants.WRAPPER)Wrapper<ResReal> wrapper);
}
