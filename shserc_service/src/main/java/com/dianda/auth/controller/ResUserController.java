package com.dianda.auth.controller;


import com.dianda.auth.bean.UserSelectParams;
import com.dianda.auth.service.IResUserService;
import com.dianda.auth.util.json.JsonResult;
import com.dianda.auth.vo.ResUserVo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * 用户控制器
 * @author huachao
 * @since 2020-08-08
 */
@RestController
@RequestMapping("/user")
public class ResUserController {

	@Resource
	IResUserService resUserService;

	@RequestMapping( path ="/list", method = RequestMethod.POST)
	public JsonResult findByPhrase(@RequestBody UserSelectParams params) {
		ResUserVo vo = resUserService.find(params);

		return JsonResult.success(vo,"");
	}

}

