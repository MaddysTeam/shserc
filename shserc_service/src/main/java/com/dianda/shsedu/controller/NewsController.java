package com.dianda.shsedu.controller;

import com.dianda.common.common.Constant;
import com.dianda.common.util.basic.DateTimeUtil;
import com.dianda.common.util.json.JsonResult;
import com.dianda.shsedu.bean.NewsSelectParams;
import com.dianda.shsedu.dto.AuditNewsDto;
import com.dianda.shsedu.dto.EditNewsDto;
import com.dianda.shsedu.entity.ShseduUser;
import com.dianda.shsedu.service.INewsService;
import com.dianda.shsedu.vo.NewsVo;
import com.dianda.shsedu.vo.NewsVoList;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("ShseduNews")
public class NewsController extends BaseController {

	 @Resource
	INewsService newsService;

	@PostMapping( path = "/list" )
	public JsonResult findByPhrase(@RequestBody NewsSelectParams params ) {
		NewsVoList list = newsService.findByPhrase ( params );
		return JsonResult.success ( list );
	}

	@PostMapping( path = "/{id}" )
	public JsonResult findById( @PathVariable long id ) {
		NewsVo o = newsService.findById ( id );
		return JsonResult.success ( o );
	}

	@PostMapping( path = "/edit" )
	public JsonResult edit(@RequestBody @Valid EditNewsDto editNewsDto , BindingResult bindingResult ) {
		if ( bindingResult.hasErrors ( ) ) {
			return JsonResult.error ( super.generateErrorMessage ( bindingResult ) );
		}

		ShseduUser loginUser = super.getLoginUserInfo ( );
		editNewsDto.setOperatorId ( loginUser.getId ( ) );
		editNewsDto.setOperateDate ( DateTimeUtil.now ( ) );

		boolean result = newsService.edit ( editNewsDto );
		return result ? JsonResult.success ( Constant.Success.EDIT_SUCCESS ) :
				JsonResult.error ( Constant.Error.EDIT_FAILURE );
	}

	@PostMapping( path = "/audit" )
	public JsonResult audit(@RequestBody @Valid AuditNewsDto auditNewsDto , BindingResult bindingResult ) {
		if ( bindingResult.hasErrors ( ) ) {
			return JsonResult.error ( super.generateErrorMessage ( bindingResult ) );
		}

		ShseduUser loginUser = getLoginUserInfo ( );
		auditNewsDto.setAuditorId ( loginUser.getId ( ) );
		boolean result = newsService.audit ( auditNewsDto );
		return result ? JsonResult.success ( Constant.Success.EDIT_SUCCESS ) :
				JsonResult.error ( Constant.Error.EDIT_FAILURE );
	}

}
