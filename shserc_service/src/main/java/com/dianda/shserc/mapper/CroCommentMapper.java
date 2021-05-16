package com.dianda.shserc.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.dianda.shserc.entity.CroComment;
import org.apache.ibatis.annotations.Param;

public interface CroCommentMapper extends BaseMapper<CroComment> {
	IPage<CroComment> selectCroComments( IPage<CroComment> page , @Param( Constants.WRAPPER ) Wrapper<CroComment> wrapper );
	int editComment( CroComment model );
	int delComment( long id );
}
