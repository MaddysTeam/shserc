package com.dianda.shserc.controller;


import com.dianda.shserc.bean.UserSelectParams;
import com.dianda.shserc.service.IResUserService;
import com.dianda.shserc.util.json.JsonResult;
import com.dianda.shserc.vo.ResUserVo;
import com.dianda.shserc.vo.ResUserVoList;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户控制器
 *
 * @author huachao
 * @since 2020-08-08
 */
@RestController
@RequestMapping("/user")
public class ResUserController {

	@Resource
	IResUserService resUserService;

	@RequestMapping(path = "/list", method = RequestMethod.POST)
	public JsonResult findByPhrase(@RequestBody UserSelectParams params) {
		ResUserVoList resUserVoList = resUserService.find(params);
		return JsonResult.success(resUserVoList);
	}

	@RequestMapping(path = "/id", method = RequestMethod.POST)
	public JsonResult findById(@RequestBody long id) {
		ResUserVo resUserVo = resUserService.getById(id);
		return JsonResult.success(resUserVo);
	}
}

