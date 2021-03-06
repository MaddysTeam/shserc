package com.dianda.shsedu.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.dianda.shsedu.entity.Columns;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ColumnsMapper extends BaseMapper<Columns> {
	List<Columns> selectColumns(@Param(Constants.WRAPPER) Wrapper<Columns> wrapper);
}
