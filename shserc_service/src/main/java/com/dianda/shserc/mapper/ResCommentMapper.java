package com.dianda.shserc.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.dianda.shserc.entity.ResComment;
import org.apache.ibatis.annotations.Param;

public interface ResCommentMapper extends BaseMapper<ResComment> {
	IPage<ResComment> selectResComments(IPage<ResComment> page, @Param(Constants.WRAPPER)Wrapper<ResComment> wrapper);
	int editComment(ResComment model);
	int delComment(long id);
}
