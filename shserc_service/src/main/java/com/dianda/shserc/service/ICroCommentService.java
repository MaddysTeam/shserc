package com.dianda.shserc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dianda.common.validators.NotNull;
import com.dianda.shserc.bean.CommentSelectParams;
import com.dianda.shserc.dto.CommentAuditDto;
import com.dianda.shserc.dto.EditCommentDto;
import com.dianda.shserc.entity.CroComment;
import com.dianda.shserc.vo.CommentVoList;

import javax.validation.Valid;

public interface ICroCommentService extends IService<CroComment> {

	CommentVoList find( CommentSelectParams params );

	boolean edit( @Valid @NotNull EditCommentDto model );
	
	boolean audit( @Valid @NotNull CommentAuditDto model );
}
