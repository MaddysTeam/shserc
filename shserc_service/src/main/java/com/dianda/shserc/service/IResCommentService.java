package com.dianda.shserc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dianda.shserc.bean.CommentSelectParams;
import com.dianda.shserc.dto.EditCommentDto;
import com.dianda.shserc.entity.ResComment;
import com.dianda.shserc.vo.CommentVoList;

public interface IResCommentService extends IService<ResComment> {

	CommentVoList find(CommentSelectParams params);

	boolean editComment(EditCommentDto model);

}
