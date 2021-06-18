package com.dianda.shserc.controller;

import com.dianda.common.util.json.JsonResult;
import com.dianda.shserc.bean.CommentSelectParams;
import com.dianda.common.common.Constant;
import com.dianda.shserc.dto.EditCommentDto;
import com.dianda.shserc.service.ICroCommentService;
import com.dianda.shserc.vo.CommentVoList;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/croComment")
public class CroCommentController {

	@Resource
	ICroCommentService service;

	@PostMapping(path="/list")
	public JsonResult findByPhrase(@RequestBody CommentSelectParams params) {
		CommentVoList list = service.find(params);
		return JsonResult.success(list);
	}

	@PostMapping(path = "/edit")
	public JsonResult edit(@RequestBody @Valid EditCommentDto editCommentDto, BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			return JsonResult.error(Constant.Error.PARAMS_IS_INVALID);
		}

		boolean result = service.edit(editCommentDto);
		return result ? JsonResult.success(Constant.Success.EDIT_SUCCESS) :
				JsonResult.error(Constant.Error.EDIT_FAILURE);
	}

}
