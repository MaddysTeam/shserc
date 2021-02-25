package com.dianda.shserc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dianda.shserc.bean.CommentSelectParams;
import com.dianda.shserc.dto.EditCommentDto;
import com.dianda.shserc.entity.ResComment;
import com.dianda.shserc.mapper.ResCommentMapper;
import com.dianda.shserc.service.IResCommentService;
import com.dianda.shserc.vo.CommentVoList;

public class ResCommentServiceImpl extends ServiceImpl<ResCommentMapper, ResComment> implements IResCommentService {

	@Override
	public CommentVoList find(CommentSelectParams params) {
		return null;
	}

	@Override
	public boolean editComment(EditCommentDto model) {
		return false;
	}

}
