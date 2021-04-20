package com.dianda.shserc.controller;

import com.dianda.common.util.json.JsonResult;
import com.dianda.shserc.common.Constant;
import com.dianda.shserc.service.IMyService;
import com.dianda.shserc.vo.ResUserVo;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@RequestMapping( "/my" )
public class MyController extends  BaseController {

	@Resource
	IMyService service;
	
	/**
	 * @title get user resource operation count  ,
	 * @desc for example :  view count, comment count,download count and favorite count
	 * @author Huachao
	 */
	@RequestMapping( path = "/operation/", method = RequestMethod.POST )
	public JsonResult resourceOperationCount( @RequestBody @Valid @Min( value = 1, message = Constant.Error.INVALID_ID ) long id , BindingResult bindingResult ) {
		if ( bindingResult.hasErrors ( ) ) {
			return JsonResult.error ( generateErrorMessage ( bindingResult ) );
		}
		
		ResUserVo resUserVo = service.findResourceOperationCount ( id );
		return JsonResult.success ( resUserVo );
	}
	
	@RequestMapping( path = "/download/", method = RequestMethod.POST )
	public JsonResult myDownloads( long id){
		return null;
	}
	
	@RequestMapping( path = "/favorite/", method = RequestMethod.POST )
	public JsonResult myFavorite(){
		return null;
	}
	
	@RequestMapping( path = "/comment/", method = RequestMethod.POST )
	public JsonResult myComment(){
		return null;
	}
	
}
