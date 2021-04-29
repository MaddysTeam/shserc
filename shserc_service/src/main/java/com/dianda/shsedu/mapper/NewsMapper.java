package com.dianda.shsedu.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.dianda.shsedu.entity.News;
import org.apache.ibatis.annotations.Param;

public interface NewsMapper extends BaseMapper<News> {
	IPage<News> selectNews( IPage<News> page, @Param( Constants.WRAPPER) Wrapper<News> wrapper);
}
